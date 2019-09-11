FROM openjdk
#VOLUME /tmp
ARG JAR_FILE
#COPY target/*.jar app.jar
#COPY 1500000 Sales Records.csv /target
COPY ["target/publisher-0.0.1-SNAPSHOT.jar", "app.jar"]
COPY ["1500000 Sales Records.csv", "/"]
ENTRYPOINT ["java","-jar","/app.jar"]
