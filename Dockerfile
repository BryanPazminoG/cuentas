FROM openjdk:21

WORKDIR /app

COPY target/cuenta-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "cuenta-0.0.1-SNAPSHOT.jar"]

