FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/creator-news.jar
CMD ["java", "-jar", "creator-news.jar"]
