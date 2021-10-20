FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-gateway-service.jar
ENTRYPOINT ["java","-jar","/final-gateway-service.jar"]
EXPOSE 9191