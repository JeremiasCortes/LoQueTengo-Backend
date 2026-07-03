# ============================================
# ETAPA 1: BUILD
# ============================================
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app

COPY gradle gradle
COPY gradlew settings.gradle.kts build.gradle.kts ./

# 🔧 Fix CRÍTICO para Windows: convertir CRLF → LF y dar permisos
RUN sed -i 's/\r$//' gradlew && chmod +x gradlew

RUN ./gradlew dependencies --no-daemon

COPY src src
RUN ./gradlew bootJar --no-daemon

# ============================================
# ETAPA 2: RUNTIME
# ============================================
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 9999
ENTRYPOINT ["java", "-jar", "app.jar"]