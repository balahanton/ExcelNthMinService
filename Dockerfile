FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
COPY target/excel-nth-min-service-0.0.1-SNAPSHOT.jar app.jar

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/excel-nth-min-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]