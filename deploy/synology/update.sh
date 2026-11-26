#!/bin/sh
set -eu

cd "$(dirname "$0")"
PATH="$PATH:/usr/local/bin:/var/packages/ContainerManager/target/usr/bin"
export PATH

if ! mkdir .deploy.lock 2>/dev/null; then
    if [ -d .deploy.lock ]; then
        printf '%s\n' 'Another Moon Hotel deployment is running.'
        exit 0
    fi
    printf '%s\n' 'Could not create the deployment lock.' >&2
    exit 1
fi
trap 'rmdir .deploy.lock' 0
trap 'exit 130' INT
trap 'exit 143' TERM

compose() {
    docker compose --project-name themoon --file compose.yaml "$@"
}

incoming_env=${THEMOON_APP_ENV_FILE:-}
container=$(compose ps --all --quiet app)
previous_image=''
if [ -n "$container" ]; then
    previous_image=$(docker inspect --format '{{.Image}}' "$container")
fi

compose pull app
if compose up --detach --pull never --wait --wait-timeout 90 app; then
    if [ -n "$incoming_env" ]; then
        mv -f "$incoming_env" app.env
    fi
    printf '%s\n' 'Moon Hotel is healthy.'
    exit 0
fi

printf '%s\n' 'The new Moon Hotel container did not become healthy.' >&2
if [ -n "$previous_image" ] && [ -f app.env ]; then
    docker image tag "$previous_image" themoon-rollback:local
    if THEMOON_IMAGE=themoon-rollback:local THEMOON_APP_ENV_FILE=app.env compose up --detach --pull never --wait --wait-timeout 90 app; then
        printf '%s\n' 'Restored the previous Moon Hotel image.' >&2
    else
        printf '%s\n' 'The previous image could not become healthy either.' >&2
    fi
fi
exit 1
