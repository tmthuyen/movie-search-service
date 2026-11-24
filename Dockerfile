# images
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# final image
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/search-service-0.0.1.jar search-service.jar


EXPOSE 8010

CMD ["java", "-jar", "search-service.jar"]

