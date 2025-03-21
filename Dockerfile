FROM maven:3.9.9-amazoncorretto-21 AS build

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]