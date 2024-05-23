FROM --platform=linux/amd64 openjdk:17-alpine

# 개인키 복사
COPY apiEncryptionKey.jks /apiEncryptionKey.jks

# jar 설정
ARG JAR_FILE=/build/libs/mail-service-1.0.jar

# jar 복사
COPY ${JAR_FILE} /MailService.jar

# PORT
# EXPOSE 9004

# Set the entry point for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/MailService.jar"]