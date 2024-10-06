# Start with an official Maven image to build the app
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the entire project into the container
COPY src ./src

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Use an official Eclipse Temurin image for Java 21 to run the app
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port on which the app runs
EXPOSE 8080

# Define the command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

## Sử dụng image maven để build ứng dụng với JDK 21
#FROM maven:3.9.9-openjdk-21 AS build
#
## Thiết lập thư mục làm việc trong container
#WORKDIR /app
#
## Sao chép file pom.xml và download dependencies
#COPY pom.xml ./
#RUN mvn dependency:go-offline -B
#
## Sao chép toàn bộ mã nguồn
#COPY src ./src
#
## Build ứng dụng
#RUN mvn clean package -DskipTests
#
## Image thứ hai: Sử dụng openjdk 21 để chạy ứng dụng
#FROM openjdk:21-slim
#
## Tạo thư mục chứa ứng dụng
#WORKDIR /app
#
## Sao chép file JAR từ giai đoạn build sang container
#COPY --from=build /app/target/*.jar app.jar
#
## Cấu hình port của ứng dụng
#EXPOSE 8080
#
## Chạy ứng dụng
#ENTRYPOINT ["java", "-jar", "app.jar"]