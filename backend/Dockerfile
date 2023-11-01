FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY ./app/build/libs/fat.jar ./

ENTRYPOINT ["java", "-jar", "fat.jar"]