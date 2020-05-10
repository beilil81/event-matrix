FROM openjdk:11.0.5-jre-slim

EXPOSE 9000

ADD /target/event-matrix-0.0.1-SNAPSHOT.jar event-matrix-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","event-matrix-0.0.1-SNAPSHOT.jar"]