FROM eclipse-temurin:17-jre-jammy

WORKDIR /app
COPY --chown=10001:10001 themoon.war /app/themoon.war
USER 10001:10001

ENV SERVER_ADDRESS=0.0.0.0 \
    SERVER_PORT=8080 \
    TZ=Asia/Seoul
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/themoon.war"]
