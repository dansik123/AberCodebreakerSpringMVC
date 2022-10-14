FROM maven:ibmjava-alpine as build-stage
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /build/src/
RUN mvn clean package

FROM openjdk:12-alpine as run-stage
WORKDIR /app
COPY --from=build-stage /build/target/AberCodebreakerMVC-0.1.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
