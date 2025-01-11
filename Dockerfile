# Etapa 1: Build
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: Execução com Distroless
FROM gcr.io/distroless/java17-debian11

WORKDIR /app

COPY --from=builder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

# Segurança: executa como usuário não root
USER nonroot

CMD ["app.jar"]