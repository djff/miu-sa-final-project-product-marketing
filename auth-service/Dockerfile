FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-auth-service.jar
ENTRYPOINT ["java","-jar","/final-auth-service.jar"]
EXPOSE 8181