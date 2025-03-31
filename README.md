# Task App API

Este projeto consiste em uma API REST para gerenciamento de tarefas colaborativas, desenvolvida com Java e utilizando o ecossistema Spring.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.3**
- **Spring Security**
- **Spring Data MongoDB**
- **MongoDB - Atlas**
- **JUnit e Mockito** (Testes unitários)
- **Maven** (Gerenciamento de dependências)
- **Docker** (Containerização da aplicação)

## Pré-requisitos

- Java 17+
- Maven
- Docker (opcional)
- Um banco de dados MongoDB configurado

## Configuração do Banco de Dados

Para que a aplicação funcione corretamente, é necessário configurar o acesso ao banco de dados no arquivo `application.properties` ou `application.yml`. Exemplo:

```properties
spring.data.mongodb.uri=mongodb://usuario:senha@host:porta/nomeDoBanco
```

## Executando a Aplicação

### Via Maven

1. Clone o repositório:
   ```sh
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_PROJETO>
   ```

2. Compile e execute o projeto:
   ```sh
   mvn spring-boot:run
   ```

### Via Docker

1. Construa a imagem Docker:
   ```sh
   docker build -t task-app-api .
   ```

2. Execute o container:
   ```sh
   docker run -p 8080:8080 task-app-api
   ```

### Verificando status da API:
  - Basta chamar o endpoint abaixo:
  ```url
  http://localhost:8080/open/status
  ```
  - Exemplo de resposta esperada ``Servidor online - 2025-03-31T14:33:22.275962800``

## Endpoints

A API expõe endpoints REST para criação e busca de usuários, efetuar login/autenticação, criação e gerenciamento de grupos e tarefas.

- Para ler a documentação da API, basta acessar:
  ```url
  localhost:8080/swagger-ui/index.html#
  ```
  Buscando por ``/v2/api-docs`` na barra de explorar.

## Autenticação e Segurança

A API utiliza **Spring Security** para autenticação. As credenciais de acesso devem ser configuradas adequadamente.
Acesse a documentação para verificar os endpoints abertos e autenticados.

## Contribuição

Sinta-se à vontade para abrir issues e pull requests para melhorias. Todas serão avaliados.

