FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-account-service.jar
ENTRYPOINT ["java","-jar","/final-account-service.jar"]
EXPOSE 8282