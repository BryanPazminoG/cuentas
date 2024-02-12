FROM openjdk:21

WORKDIR /app

COPY target/cuenta-0.0.1-SNAPSHOT.jar .

EXPOSE 8084

CMD ["java", "-jar", "cuenta-0.0.1-SNAPSHOT.jar"]

