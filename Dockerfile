FROM openjdk:11
ARG JAR_FILE
COPY ${JAR_FILE} first.jar
ENTRYPOINT ["java", "-jar", "/first.jar"]