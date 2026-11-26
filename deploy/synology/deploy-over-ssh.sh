#!/usr/bin/env bash
set -euo pipefail

: "${NAS_HOST:?Add NAS_HOST to the production environment secrets}"
: "${NAS_SSH_USER:?Add NAS_SSH_USER to the production environment secrets}"
: "${NAS_SSH_KEY:?Add NAS_SSH_KEY to the production environment secrets}"
: "${NAS_KNOWN_HOSTS:?Add NAS_KNOWN_HOSTS to the production environment secrets}"
: "${NAS_APP_ENV:?Add NAS_APP_ENV to the production environment secrets}"
: "${NAS_SSH_PORT:?NAS SSH port is required}"
: "${NAS_PROJECT_DIR:?NAS project directory is required}"
: "${RELEASE_IMAGE:?The published image digest is required}"
: "${GHCR_USER:?GitHub registry username is required}"
: "${GHCR_TOKEN:?GitHub registry token is required}"

if [[ ! "$NAS_HOST" =~ ^[A-Za-z0-9][A-Za-z0-9.-]*$ || ! "$NAS_SSH_USER" =~ ^[A-Za-z0-9_][A-Za-z0-9_.-]*$ ]]; then
    echo 'Use a DNS name or IPv4 address and a NAS account name.' >&2
    exit 1
fi
if [[ ! "$NAS_SSH_PORT" =~ ^[1-9][0-9]{0,4}$ ]] || (( NAS_SSH_PORT > 65535 )); then
    echo 'NAS_SSH_PORT must be a valid TCP port.' >&2
    exit 1
fi
if [[ ! "$NAS_PROJECT_DIR" =~ ^(/[A-Za-z0-9_.-]+)+$ || "$NAS_PROJECT_DIR/" == */../* || "$NAS_PROJECT_DIR/" == */./* ]]; then
    echo 'NAS_PROJECT_DIR must be an absolute NAS directory.' >&2
    exit 1
fi
if [[ ! "$RELEASE_IMAGE" =~ ^ghcr\.io/[a-z0-9][a-z0-9./_-]*@sha256:[a-f0-9]{64}$ || ! "$GHCR_USER" =~ ^[A-Za-z0-9_-]+(\[bot\])?$ ]]; then
    echo 'The registry user or published image digest is invalid.' >&2
    exit 1
fi

base_dir=$(cd "$(dirname "$0")" && pwd)
umask 077
workspace=$(mktemp -d)
remote="$NAS_SSH_USER@$NAS_HOST"
ssh_options=(-F /dev/null -i "$workspace/deploy-key" -o "Port=$NAS_SSH_PORT"
    -o BatchMode=yes -o IdentitiesOnly=yes -o StrictHostKeyChecking=yes
    -o "UserKnownHostsFile=$workspace/known_hosts" -o ConnectTimeout=15)
staging_dir=''
cleanup() {
    if [[ -n "$staging_dir" ]]; then
        ssh "${ssh_options[@]}" "$remote" "rm -rf '$staging_dir'" </dev/null || true
    fi
    rm -rf "$workspace"
}
trap cleanup EXIT
printf '%s\n' "$NAS_SSH_KEY" > "$workspace/deploy-key"
printf '%s\n' "$NAS_KNOWN_HOSTS" > "$workspace/known_hosts"
printf '%s\n' "$NAS_APP_ENV" > "$workspace/app.env"

candidate_dir=$(ssh "${ssh_options[@]}" "$remote" \
    "umask 077; mkdir -p '$NAS_PROJECT_DIR' && mktemp -d '$NAS_PROJECT_DIR/.release.XXXXXXXX'")
if [[ "$candidate_dir" != "$NAS_PROJECT_DIR"/.release.* || ! "$candidate_dir" =~ /\.release\.[A-Za-z0-9]+$ || "${candidate_dir%/*}" != "$NAS_PROJECT_DIR" ]]; then
    echo 'The NAS did not return a valid staging directory.' >&2
    exit 1
fi
staging_dir=$candidate_dir

cat > "$workspace/activate.sh" <<'REMOTE_SCRIPT'
#!/bin/sh
set -eu
umask 077
staging_dir=$(cd "$(dirname "$0")" && pwd)
project_dir=$1
release_image=$2
registry_user=$3
export PATH="$PATH:/usr/local/bin:/var/packages/ContainerManager/target/usr/bin"
export DOCKER_CONFIG="$staging_dir/docker"
mkdir "$DOCKER_CONFIG"
docker login ghcr.io --username "$registry_user" --password-stdin
mv -f "$staging_dir/compose.yaml" "$staging_dir/update.sh" "$project_dir/"
THEMOON_IMAGE="$release_image" THEMOON_APP_ENV_FILE="$staging_dir/app.env" /bin/sh "$project_dir/update.sh"
REMOTE_SCRIPT

tar -c -f - -C "$base_dir" compose.yaml update.sh -C "$workspace" app.env activate.sh |
    ssh "${ssh_options[@]}" "$remote" \
        "umask 077; tar -x -f - -C '$staging_dir' && chmod 600 '$staging_dir/app.env'"
printf '%s\n' "$GHCR_TOKEN" | ssh "${ssh_options[@]}" "$remote" \
    "/bin/sh '$staging_dir/activate.sh' '$NAS_PROJECT_DIR' '$RELEASE_IMAGE' '$GHCR_USER'"
