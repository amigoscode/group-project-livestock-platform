FROM eclipse-temurin:17-jdk-jammy

MAINTAINER moeB
RUN mkdir -p /livestock-platform-backend
ADD target /livestock-platform-backend
RUN ls /livestock-platform-backend
ENTRYPOINT ["bash","start-app.sh"]