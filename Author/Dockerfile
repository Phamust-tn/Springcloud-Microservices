FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/Author-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]