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

NAS에서는 Container Manager의 Docker Compose v2로 다음 두 파일을 사용할 폴더에 복사합니다.

- [compose.yaml](deploy/synology/compose.yaml)
- [update.sh](deploy/synology/update.sh)

그 폴더에 배포 설정 파일 `.env`를 만듭니다. 이미지는 워크플로를 실행하는 저장소 이름을 소문자로 적습니다. 아래 값은 원본 저장소 기준입니다.

```dotenv
THEMOON_IMAGE=ghcr.io/the-moon-hotel/finalproj_moon:latest
THEMOON_BIND_ADDRESS=127.0.0.1
THEMOON_PORT=18080
```

같은 폴더의 `app.env`에 NAS에서 접속할 수 있는 앱 전용 DB 계정을 설정합니다. 기존 Mac의 DB 계정이 접속 호스트로 제한되어 있다면 NAS 컨테이너의 접속도 DB 계정에 허용되어 있어야 합니다.

```dotenv
SPRING_DATASOURCE_URL=jdbc:mariadb://2je7.synology.me:3306/themoon?sslMode=verify-full
SPRING_DATASOURCE_USERNAME=themoon_app
SPRING_DATASOURCE_PASSWORD='여기에_NAS용_DB_비밀번호'
```

GHCR 이미지가 비공개라면 NAS 작업을 실행할 계정에서 `docker login ghcr.io --username <GitHub 아이디>`로 로그인합니다. 비밀번호 입력에는 `read:packages` 권한의 GitHub classic PAT를 사용합니다. [GitHub Container Registry 인증 문서](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-container-registry#authenticating-to-the-container-registry)

DSM의 **제어판 → 작업 스케줄러 → 사용자 정의 스크립트**에 이 명령을 등록합니다. 경로는 두 파일을 복사한 NAS 폴더에 맞추고, 예를 들어 5분 간격으로 실행합니다. [Synology 작업 스케줄러 문서](https://kb.synology.com/en-uk/DSM/help/DSM/AdminCenter/system_taskscheduler)

```sh
/bin/sh /volume1/docker/themoon/update.sh
```

새 이미지가 공지사항 페이지를 통해 DB 연결 검사를 통과할 때까지 기다립니다. 검사에 실패하면 기존 컨테이너 이미지로 복구를 시도합니다. DSM 리버스 프록시를 사용한다면 대상은 기본값인 `127.0.0.1:18080`입니다.
