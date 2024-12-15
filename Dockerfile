FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=t2scd-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
RUN apt-get update && apt-get install -y iputils-ping
ENTRYPOINT ["java","-jar","/app.jar"]
