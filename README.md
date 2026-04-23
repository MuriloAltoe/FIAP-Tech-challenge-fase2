# FIAP Tech Challenge - Fase 2

API REST para gerenciamento de biblioteca com cadastro de livros e usuários, controle de empréstimos/devoluções e relatórios operacionais.

## Repositório

https://github.com/MuriloAltoe/FIAP-Tech-challenge-fase2

## Stack

- Java 17
- Spring Boot 4.0.5
- Spring Data JPA
- Spring Validation
- PostgreSQL
- OpenAPI (springdoc)
- Docker / Docker Compose

## Funcionalidades

- CRUD de livros
- CRUD de usuários
- Empréstimo e devolução de livros
- Relatórios de empréstimos ativos, atrasados e disponibilidade de livros
- Tratamento global de exceções com payload padronizado

## Executando localmente

```bash
./mvnw spring-boot:run
```

Base URL: `http://localhost:8080`

Swagger UI: `http://localhost:8080/swagger-ui.html`

## Executando com Docker

```bash
docker-compose up --build
```

## Documentação

- Documentação técnica (Markdown): `docs/DOCUMENTACAO_TECNICA.md`
- Relatório técnico (Markdown): `docs/RELATORIO_TECNICO.md`
- Relatório técnico (PDF): `docs/RELATORIO_TECNICO.pdf`