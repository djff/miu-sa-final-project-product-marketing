FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-notification-service.jar
ENTRYPOINT ["java","-jar","/final-notification-service.jar"]
EXPOSE 8289