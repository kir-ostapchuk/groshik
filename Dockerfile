FROM gradle:7.1.1-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM openjdk:11.0.4-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/groshik-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/groshik-0.0.1-SNAPSHOT.jar"]