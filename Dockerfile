FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/uposts-0.0.1-SNAPSHOT.jar uposts.jar
ENTRYPOINT [ "java", "-jar", "uposts.jar"] 