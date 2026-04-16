# Documentação Técnica - Library API

## Visão Geral

API REST para gerenciamento de biblioteca com cadastro de livros e usuários, controle de empréstimos/devoluções e relatórios operacionais.

- Base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Stack

- Java 17
- Spring Boot 4.0.5
- Spring Data JPA
- Spring Validation
- PostgreSQL
- springdoc-openapi
- Docker / Docker Compose

## Arquitetura

- `interfaces.controller`: endpoints REST
- `application.service`: regras de negócio
- `application.dto`: contratos de entrada/saída
- `application.mapper`: conversões entidade/DTO
- `domain.entities`: modelo de domínio
- `domain.repository`: persistência JPA
- `infrastructure`: OpenAPI e exception handling

## Entidades

- `Book`: id, title, author, isbn, available
- `Users`: id, nome, email
- `Loan`: id, user, book, loanDate, expectedReturnDate, returnDate

Relacionamentos:
- `Users` 1:N `Loan`
- `Book` 1:N `Loan`

## Endpoints

### Books
- `POST /books`
- `GET /books/{id}`
- `GET /books`
- `PUT /books/{id}`
- `DELETE /books/{id}`

### Users
- `POST /users`
- `GET /users/{id}`
- `GET /users`
- `PUT /users/{id}`
- `DELETE /users/{id}`

### Loans
- `POST /loans` (empréstimo)
- `POST /loans/{id}/return` (devolução)
- `GET /loans/{id}`
- `GET /loans`

### Reports
- `GET /reports/loans/active`
- `GET /reports/loans/overdue`
- `GET /reports/books/availability`

## Regras de Negócio

- Livro só pode ser emprestado se estiver disponível.
- Empréstimo define `loanDate=hoje` e, se ausente, `expectedReturnDate=hoje+7 dias`.
- `expectedReturnDate` não pode estar no passado.
- Devolução marca `returnDate=hoje` e devolve disponibilidade ao livro.
- Não permite excluir livro/usuário com empréstimo ativo.
- Operações de emprestar/devolver são transacionais (`@Transactional`).

## Validação

- `BookRequest`: `title`, `author`, `isbn` obrigatórios.
- `UsersRequest`: `nome` obrigatório, `email` obrigatório e válido.
- `LoanRequest`: `userId` e `bookId` obrigatórios.

## Erros

Formato padrão (`ApiErrorResponse`):

```json
{
  "timestamp": "2026-04-16T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Book is not available for loan",
  "path": "/loans"
}
```

Mapeamento:
- `ResourceNotFoundException` -> 404
- `BusinessRuleException` -> 400
- `MethodArgumentNotValidException` -> 400
- `Exception` -> 500

## Execução

### Local

```bash
./mvnw spring-boot:run
```

### Docker

```bash
docker-compose up --build
```
