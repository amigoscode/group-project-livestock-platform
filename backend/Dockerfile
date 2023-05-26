FROM eclipse-temurin:17-jdk-jammy

MAINTAINER moeB
WORKDIR target

RUN pwd
RUN ls
COPY livestock-platform-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["bash","start-app.sh"]