# syntax=docker/dockerfile:1

FROM gradle:7.5.1-jdk11-alpine as build
WORKDIR /workspace/app

COPY build.gradle.kts settings.gradle.kts gradlew gradle.properties ./
COPY src src

RUN gradle build --no-daemon --debug

FROM eclipse-temurin:11-jdk-alpine as dominant-colors-extractor
EXPOSE 8080
VOLUME /app
COPY --from=build /workspace/app/build/libs/dominant-colors-extractor-all.jar ./app/
ENTRYPOINT ["java","-jar", "./app/dominant-colors-extractor-all.jar"]
