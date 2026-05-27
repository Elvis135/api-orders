# ====== Stage 1: Build ======
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar pom primero para descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto
RUN mvn clean package -DskipTests

# ====== Stage 2: Run ======
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiar el jar generado desde el builder
COPY --from=builder /app/target/*.jar app.jar

# Puerto interno de Spring Boot
EXPOSE 8080

# Ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]