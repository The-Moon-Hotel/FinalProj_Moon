# The Moon Hotel

## DB와 실행

기본 DB는 `2je7.synology.me:3306/themoon`입니다. 새 DB를 준비할 때는 MariaDB에서 `themoon`을 만든 후 [db/schema.sql](db/schema.sql)을 적용합니다. 스크립트는 지점 3곳, 지점별 객실 항목 4개, 부대시설 4개를 등록합니다. 기존 데이터가 있는 DB를 복원할 때는 먼저 백업과 스키마를 확인하세요.

프로젝트 루트의 `.env.local`에 앱에서 사용할 DB 계정을 설정합니다. 파일은 Git에서 제외됩니다. 환경 변수로도 같은 설정을 전달할 수 있습니다.

```properties
SPRING_DATASOURCE_USERNAME=themoon_app
SPRING_DATASOURCE_PASSWORD=여기에_DB_비밀번호
```

Java 11 이상에서 다음 명령을 실행한 뒤 [로컬 사이트](http://127.0.0.1:8080/)를 엽니다.

```bash
bash gradlew bootRun
```

다른 DB를 사용할 때는 `SPRING_DATASOURCE_URL`에 JDBC 주소를 지정합니다. 새 DB에서 사이트 관리자 계정이 필요한 경우, 회원가입 후 해당 회원의 `guest.sys` 값을 `2`로 설정합니다.

## GitHub Actions와 Synology 배포

`dev`는 개발 브랜치이며, 배포할 변경은 `master`에 병합합니다. [CI/CD 워크플로](.github/workflows/ci-cd.yml)는 브랜치 푸시와 `dev`·`master` 대상 PR에서 Java 17로 테스트를 실행하고 `themoon-war` 산출물을 남깁니다.

NAS용 이미지는 `master`에 포함된 커밋에 `v1.0.0` 형식의 버전 태그를 푸시하고 테스트가 통과했을 때 게시됩니다. GitHub Container Registry에 버전, `latest`, `sha-<커밋 SHA>`를 붙이며 amd64와 arm64를 지원합니다. [GitHub의 이미지 게시 문서](https://docs.github.com/en/actions/tutorials/publish-packages/publish-docker-images)

```bash
git fetch origin master
git tag -a v1.0.0 -m "Release v1.0.0" origin/master
git push origin refs/tags/v1.0.0
```

이미지가 게시되면 GitHub Actions가 NAS에 SSH로 연결해 해당 이미지의 다이제스트로 Container Manager를 업데이트합니다. NAS 배포 계정은 Docker Compose v2를 실행하고 배포 폴더에 파일을 쓸 수 있어야 합니다.

GitHub 저장소의 **Settings → Environments → production → Environment secrets**에 아래 값을 등록합니다. [GitHub 환경 시크릿 안내](https://docs.github.com/en/actions/how-tos/deploy/configure-and-manage-deployments/manage-environments)

| Secret | 값 |
| --- | --- |
| `NAS_HOST` | GitHub Actions 실행기가 접속할 수 있는 NAS SSH 호스트 |
| `NAS_SSH_USER` | NAS의 배포 계정 |
| `NAS_SSH_KEY` | 해당 계정에 등록한 배포용 SSH 개인 키 |
| `NAS_KNOWN_HOSTS` | NAS 호스트 키를 검증한 `known_hosts` 항목. 기본 포트가 아니면 `[호스트]:포트`로 기록합니다. |
| `NAS_APP_ENV` | 아래 형식의 앱 환경 파일 전체 |

운영 DB 계정은 NAS 컨테이너에서 접속할 수 있어야 합니다. DB 비밀번호와 같은 민감한 값은 `NAS_APP_ENV`에 입력합니다.

```dotenv
SPRING_DATASOURCE_URL=jdbc:mariadb://2je7.synology.me:3306/themoon?sslMode=verify-full
SPRING_DATASOURCE_USERNAME=themoon_app
SPRING_DATASOURCE_PASSWORD='여기에_NAS용_DB_비밀번호'
```

SSH 포트와 배포 폴더는 필요할 때 **Environment variables**의 `NAS_SSH_PORT`와 `NAS_PROJECT_DIR`로 지정합니다. 기본값은 `22`와 `/volume1/docker/themoon`입니다. 앱 포트는 NAS 배포 폴더의 `.env`에 `THEMOON_BIND_ADDRESS`와 `THEMOON_PORT`로 설정할 수 있습니다. DSM 리버스 프록시의 기본 대상은 `127.0.0.1:18080`입니다.

새 컨테이너는 공지사항 페이지에서 DB 연결 검사를 통과해야 합니다. 검사에 실패하면 직전 이미지와 설정으로 복구를 시도합니다.
