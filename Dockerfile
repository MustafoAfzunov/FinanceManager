# FROM maven:3.8.5-openjdk-17 AS build
# COPY . .
# RUN mvn clean package -DskipTests

# FROM openjdk:17.0.1-jdk-slim
# COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","demo.jar"]

# Use the Eclipse Temurin Alpine official image
# https://hub.docker.com/_/eclipse-temurin
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy local code to the container image
COPY . ./

# Ensure the `mvnw` script has executable permissions
RUN chmod +x mvnw

# Debugging: Check if `mvnw` exists and is executable
RUN ls -l mvnw && ./mvnw --version

# Build the app
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Run the app dynamically finding the JAR file in the target directory
CMD ["sh", "-c", "java -jar target/*.jar"]
