FROM maven:3.9.9 AS builder
WORKDIR /BoekWT
COPY pom.xml ./

RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn clean install

FROM openjdk:22-slim
WORKDIR /BoekWT
EXPOSE 8080
COPY --from=builder /BoekWT/target/*.jar BoekWT.jar
ENTRYPOINT ["java", "-jar", "/BoekWT/BoekWT.jar"]
