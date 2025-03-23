FROM eclipse-temurin:23-jdk AS builder

COPY build/libs/currency_project_tbank-1.0-SNAPSHOT.jar app_tbank.jar
EXPOSE 8080
CMD ["java", "-jar", "app_tbank.jar"]
