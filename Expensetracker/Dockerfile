FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/Expensetracker-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]
