FROM --platform=linux/amd64 openjdk:17-alpine


# jar 설정
ARG JAR_FILE=/build/libs/eureka-service-1.0.jar

# jar 복사
COPY ${JAR_FILE} /EurekaService.jar

# PORT
EXPOSE 8761

# Set the entry point for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/EurekaService.jar"]