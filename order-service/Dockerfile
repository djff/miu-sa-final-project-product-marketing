FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-order-service.jar
ENTRYPOINT ["java","-jar","/final-order-service.jar"]
EXPOSE 9292