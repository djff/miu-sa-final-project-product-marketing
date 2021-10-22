FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} affiliate-service.jar
ENTRYPOINT ["java","-jar","/affiliate-service.jar"]
EXPOSE 8383
