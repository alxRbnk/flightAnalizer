FROM openjdk:21

WORKDIR /app

COPY ./files/tickets.json /app/files/tickets.json
COPY ./target/flightanalizer-1.0-SNAPSHOT-jar-with-dependencies.jar /app/flightanalizer.jar

CMD ["java", "-jar", "flightanalizer.jar"]