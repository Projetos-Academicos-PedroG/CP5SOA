# Auto Escola 3ESS

API REST desenvolvida para o gerenciamento de uma Auto Escola, permitindo o controle de alunos, instrutores, usuários e agendamentos de instruções.

O projeto foi desenvolvido utilizando **Spring Boot** e evoluído para o CP5 com integração a uma API externa, documentação com Swagger/OpenAPI, configuração de CORS e testes automatizados.

---

## Sobre o Projeto

A **Auto Escola 3ESS** é uma API REST responsável pelo gerenciamento das principais operações de uma auto escola.

O sistema permite:

- Cadastro e gerenciamento de alunos
- Cadastro e gerenciamento de instrutores
- Agendamento de instruções
- Gerenciamento de usuários
- Autenticação utilizando JWT
- Consulta de endereços através do CEP
- Documentação da API utilizando Swagger/OpenAPI

---

## Tecnologias Utilizadas

### Backend

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT
- Bean Validation
- Lombok
- Flyway
- MySQL

### Testes

- JUnit 5
- AssertJ
- Spring Boot Test
- Spring Data JPA Test
- H2 Database

### Documentação

- Swagger
- OpenAPI

### API Externa

- [ViaCEP](https://viacep.com.br/)

---

## Arquitetura

O projeto utiliza uma arquitetura organizada por responsabilidades:

```text
src
└── main
    ├── java
    │   └── br.com.fiap3ess.autoescola3ess
    │       ├── controller
    │       ├── domain
    │       ├── infra
    │       │   ├── exception
    │       │   └── security
    │       ├── service
    │       └── config
    │           └── documentation
    │
    └── resources
        └── db.migration
