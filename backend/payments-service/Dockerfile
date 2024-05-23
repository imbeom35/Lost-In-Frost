FROM --platform=linux/amd64 openjdk:17-alpine

# 개인키 복사
COPY apiEncryptionKey.jks /apiEncryptionKey.jks

# jar 설정
ARG JAR_FILE=/build/libs/payments-service-1.0.jar

# jar 복사
COPY ${JAR_FILE} /PaymentsService.jar

# PORT
<<<<<<< HEAD
# EXPOSE 9006
=======
#EXPOSE 9006
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

# Set the entry point for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/PaymentsService.jar"]