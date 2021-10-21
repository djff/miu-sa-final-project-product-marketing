FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-shipping-service.jar
ENTRYPOINT ["java","-jar","/final-shipping-service.jar"]
EXPOSE 8081