FROM eclipse-temurin:25-jdk-alpine  AS build

WORKDIR /app

RUN apk add --no-cache maven

COPY . .

RUN mvn clean package -DskipTests -pl client-oauth2 -am

FROM eclipse-temurin:25-jre-alpine
RUN apk add --no-cache curl bash
WORKDIR /app

COPY --from=build /app/client-oauth2/target/*.jar app.jar

EXPOSE 9099

ENTRYPOINT ["java", "-jar", "app.jar"]