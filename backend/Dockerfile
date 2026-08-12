FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /workspace

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew

COPY src src
COPY config config

RUN ./gradlew bootJar --no-daemon  saadsdsa


FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

RUN apk add --no-cache curl

COPY --from=builder /workspace/build/libs/serviceflow.jar serviceflow.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "serviceflow.jar"]