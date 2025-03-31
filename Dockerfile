# Fase de build
FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /app

# Copia apenas os arquivos necessários primeiro (evita reconstrução desnecessária)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Agora copia o restante dos arquivos
COPY src ./src

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Fase final (imagem mais leve)
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copia o JAR gerado para a imagem final
COPY --from=build /app/target/*.jar /app/app.jar
COPY .env /app/.env

# Instala bash para carregar variáveis de ambiente (opcional)
RUN apk add --no-cache bash

# Comando para rodar a aplicação carregando o .env
CMD export $(grep -v '^#' /app/.env | xargs) && java -jar app.jar