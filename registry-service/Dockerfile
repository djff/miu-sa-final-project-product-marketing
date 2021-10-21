FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} final-registry-service.jar
ENTRYPOINT ["java","-jar","/final-registry-service.jar"]
EXPOSE 8761