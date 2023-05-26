FROM eclipse-temurin:17-jdk-jammy

MAINTAINER moeB
RUN pwd
RUN ls
WORKDIR target
COPY livestock-platform-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["bash","start-app.sh"]