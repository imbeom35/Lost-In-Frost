FROM --platform=linux/amd64 openjdk:17-alpine

# 개인키 복사
COPY apiEncryptionKey.jks /apiEncryptionKey.jks

# jar 설정
ARG JAR_FILE=/build/libs/user-service-1.0.jar

# jar 복사
COPY ${JAR_FILE} /UserService.jar

# PORT
<<<<<<< HEAD
# EXPOSE 9002
=======
#EXPOSE 9002
>>>>>>> 54f0d187adfeb0db8f914fde189a7abd8635d626

# Set the entry point for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod","/UserService.jar"]