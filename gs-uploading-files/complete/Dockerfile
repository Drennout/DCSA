FROM maven:3.8.6-ibm-semeru-17-focal

ADD . /app
WORKDIR /app

RUN mvn clean install -DskipTests

FROM openjdk:17-oracle

ARG JAR_FILE=/app/target/*.jar
COPY --from=0 ${JAR_FILE} /application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
