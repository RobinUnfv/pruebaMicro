FROM eclipse-temurin:25-jdk-alpine  AS build

WORKDIR /app

RUN apk add --no-cache maven

COPY . .

RUN mvn clean package -DskipTests -pl msvc-compras -am

FROM eclipse-temurin:25-jre-alpine
RUN apk add --no-cache curl bash
WORKDIR /app

COPY --from=build /app/msvc-compras/target/*.jar app.jar

EXPOSE 7020

ENTRYPOINT ["java", "-jar", "app.jar"]