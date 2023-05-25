FROM eclipse-temurin:17-jdk-jammy

MAINTAINER moeB
WORKDIR backend
RUN pwd
COPY target/*.jar app.jar
ENTRYPOINT ["bash","start-app.sh"]