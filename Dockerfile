# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom first to cache deps
COPY pom.xml .
# Pre-download dependencies to speed up builds
RUN mvn -B -q -e -DskipTests dependency:go-offline

# Copy sources and build
COPY src src
RUN mvn -B -q clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

# copy built jar
COPY --from=build /app/target/*.jar app.jar

# Optional: use smaller heap in tiny instances
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Bind to Render's port
CMD ["sh","-c","java -Dserver.port=${PORT:-8080} -jar app.jar"]


