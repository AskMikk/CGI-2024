FROM maven:3.9.6-eclipse-temurin-21 as builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src

RUN mvn package -DskipTests
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=builder /app/target/recommendations-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "recommendations-0.0.1-SNAPSHOT.jar"]