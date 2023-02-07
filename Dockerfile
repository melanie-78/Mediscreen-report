FROM openjdk:8

EXPOSE 8084

ADD target/*.jar mediscreen-report-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/mediscreen-report-0.0.1-SNAPSHOT.jar"]