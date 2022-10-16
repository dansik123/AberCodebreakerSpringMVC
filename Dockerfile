FROM maven:3.8.6-openjdk-8-slim
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src/ /build/src/
RUN mvn clean package
ENTRYPOINT ["java", "-jar", "/build/target/AberCodebreakerMVC-0.1.jar"]
