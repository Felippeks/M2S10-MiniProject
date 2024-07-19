# MiniProjeto - Sistema de Gerenciamento de Médicos

Este projeto é uma API REST desenvolvida com Spring Boot para o gerenciamento de médicos. Permite operações CRUD (Criar, Ler, Atualizar, Deletar) sobre médicos, além de oferecer funcionalidades como filtragem por nome, especialidade e data de nascimento, e tratamento de exceções para melhor feedback ao usuário.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- JPA/Hibernate
- PostgreSQL

## Pré-requisitos

Para executar este projeto, você precisará ter instalado:

- JDK 11 ou superior
- Maven
- PostgreSQL

## Configuração do Banco de Dados

1. Crie um banco de dados no PostgreSQL.
2. Atualize o arquivo `src/main/resources/application.properties` com as configurações do seu banco de dados:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
````

## Como Executar
Clonar o Repositório
Para clonar o projeto para a sua máquina local, execute o seguinte comando no terminal:
git clone https://github.com/Felippeks/M2S10-MiniProject.git

## Compilar e Executar
Dentro do diretório do projeto, execute os seguintes comandos para compilar e iniciar a aplicação:
mvn clean install
mvn spring-boot:run

Endpoints Disponíveis:

- POST /api/medicos: Cadastra um novo médico.
- GET /api/medicos: Lista todos os médicos.
- GET /api/medicos/list: Lista os médicos com paginação e filtros opcionais.
- PUT /api/medicos/{id}: Atualiza os dados de um médico.
- DELETE /api/medicos/{id}: Exclui um médico pelo ID.
- GET /api/medicos/crm/{crm}: Busca um médico pelo CRM.
- GET /api/medicos/{id}: Busca um médico pelo ID.

## Tratamento de Erros
A API fornece respostas de erro detalhadas com códigos de status HTTP apropriados e mensagens de erro claras para facilitar a identificação e correção de problemas.  

## Testando a API
Você pode testar a API usando ferramentas como Postman ou Insomnia, enviando requisições HTTP para os endpoints definidos.  
Contribuindo
Para contribuir com o projeto, por favor, crie um fork do repositório, faça suas alterações e envie um Pull Request.

```Este README fornece uma visão geral do projeto, instruções de configuração, execução, e uma descrição dos endpoints disponíveis.```
