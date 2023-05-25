FROM eclipse-temurin:17-jdk-jammy

MAINTAINER moeB
WORKDIR backend
RUN pwd
RUN ls
COPY target/*.jar app.jar
ENTRYPOINT ["bash","start-app.sh"]