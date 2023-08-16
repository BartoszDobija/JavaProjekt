FROM openjdk:17
MAINTAINER works.buddy
WORKDIR /app
COPY target/libs/* /app/libs/
COPY src/main/resources /app/resources
COPY target/library.jar /app/library.jar
ENTRYPOINT ["java", "-jar", "library.jar"]