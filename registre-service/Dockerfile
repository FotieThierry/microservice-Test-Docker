FROM openjdk:17-oracle
VOLUME /tmp
#CMD ["./mvmw", "clean", "package"]
COPY target/registre-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

