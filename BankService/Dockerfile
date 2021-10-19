FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bank-service.jar
ENTRYPOINT ["java","-jar","/bank-service.jar"]
EXPOSE 9494