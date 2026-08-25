# ==================== ETAPA 1: BUILD ====================
FROM eclipse-temurin:25-jdk-alpine AS build

WORKDIR /app

# Instalar Maven
RUN apk add --no-cache maven

# Copiar el código fuente
COPY . .

# Compilar e instalar (mvn clean install)
# -pl discovery-server: solo el módulo discovery-server
# -am: también construye sus dependencias
# -DskipTests: omite pruebas (ajústalo según necesites)
RUN mvn clean install -DskipTests -pl discovery-server -am

# ==================== ETAPA 2: RUNTIME ====================
FROM eclipse-temurin:25-jre-alpine

# Instalar utilidades opcionales (curl, bash)
RUN apk add --no-cache curl bash

WORKDIR /app

# Copiar el JAR generado desde la etapa de build
# Asumiendo que el JAR se genera en discovery-server/target/
# Usamos un patrón más específico para evitar copiar múltiples JARs
COPY --from=build /app/discovery-server/target/discovery-server-*.jar app.jar

# Exponer el puerto (por defecto 8761 para Eureka)
EXPOSE 8761

# ==================== CONFIGURACIÓN DE PERFIL ====================
# Definir el perfil activo por defecto (se puede sobrescribir al ejecutar)
ENV DISCOVERY_SERVICE_PROFILE=server01

# Punto de entrada: ejecutar el JAR con el perfil activo
ENTRYPOINT ["sh", "-c", "java -jar app.jar --spring.profiles.active=${DISCOVERY_SERVICE_PROFILE}"]