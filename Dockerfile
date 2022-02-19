FROM openjdk:17-alpine

EXPOSE 8080

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} DataWarehouse-0.0.1.jar

ENTRYPOINT ["java","-jar","DataWarehouse-0.0.1.jar"]

