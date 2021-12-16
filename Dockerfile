FROM openjdk:11
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} pay.jar
ENTRYPOINT ["java","-Dspring.profiles.active=pod","-jar","/pay.jar"]
EXPOSE 8080