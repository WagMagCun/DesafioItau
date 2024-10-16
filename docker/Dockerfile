# Dockerfile
FROM openjdk:17-slim
WORKDIR /app
COPY build/libs/transfer-api-1.0.0.jar /app/transfer-api-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/app/transfer-api-1.0.0.jar"]
EXPOSE 8080