FROM java:8
EXPOSE 8888
ARG JAR_FILE
ADD target/${JAR_FILE} /ieasy-server.jar
ENTRYPOINT ["java", "-jar","/ieasy-server.jar"]
