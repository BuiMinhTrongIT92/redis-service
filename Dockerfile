FROM eclipse-temurin:17-jre
MAINTAINER trongbui
WORKDIR /app
COPY target/redis-service-0.0.1-SNAPSHOT.jar /redis-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/redis-service-0.0.1-SNAPSHOT.jar"]