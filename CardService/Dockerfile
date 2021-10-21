FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} card-service.jar
ENTRYPOINT ["java","-jar","/card-service.jar"]
EXPOSE 9595