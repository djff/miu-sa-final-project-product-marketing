FROM openjdk:16-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-payment-service.jar
ENTRYPOINT ["java","-jar","/final-payment-service.jar"]
EXPOSE 9696