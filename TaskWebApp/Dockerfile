FROM openjdk
VOLUME /tmp
ARG JAR_FILE
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]