# Use Java 21 JDK image
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first (to cache dependencies)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (cached layer)
RUN ./mvnw dependency:go-offline

# Copy the rest of the project
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Run the Spring Boot app
CMD ["java", "-Dserver.port=${PORT}", "-jar", "target/*.jar"]
