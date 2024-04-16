FROM openjdk:17-jdk
COPY build/libs/blog-service-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "/app.jar"]