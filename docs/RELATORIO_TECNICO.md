# Relatório Técnico - Library API

## Objetivo

Reconstrução completa do projeto após perda de arquivos, mantendo arquitetura, regras de negócio e endpoints de CRUD, empréstimo/devolução e relatórios.

## Escopo Entregue

- Estrutura Maven e Spring Boot restaurada
- Camadas de domínio, aplicação, interfaces e infraestrutura restauradas
- Endpoints de livros, usuários, empréstimos e relatórios restaurados
- Tratamento global de exceções restaurado
- Validações de entrada com Bean Validation restauradas
- OpenAPI/Swagger restaurado
- Dockerfile e docker-compose restaurados
- Documentação técnica e relatório técnico restaurados

## Decisões Técnicas Mantidas

- Arquitetura em camadas para separação de responsabilidades
- DTOs + mappers para desacoplamento entre API e entidades
- Exceções semânticas (`404`/`400`) em vez de erros genéricos
- Transações em operações críticas (`LoanService`)
- Repositórios com consultas derivadas do Spring Data

## Principais Riscos Mitigados

1. Inconsistência entre disponibilidade de livro e estado do empréstimo
- Mitigação: métodos transacionais em `createLoan` e `returnLoan`.

2. Exclusão indevida de entidades com vínculo ativo
- Mitigação: bloqueio de exclusão de `Book`/`Users` com empréstimo em aberto.

3. Falta de padronização de erro
- Mitigação: `GlobalExceptionHandler` com payload único.

4. Entrada inválida de dados
- Mitigação: validações nos DTOs e `@Valid` nos controllers.

## Resultado

Projeto refeito e pronto para build/execução com os mesmos comportamentos funcionais definidos anteriormente.

## Próximos Passos Recomendados

1. Criar testes automatizados unitários e de integração.
2. Incluir paginação nos endpoints de listagem.
3. Adotar migrações versionadas (Flyway/Liquibase).
4. Implementar autenticação/autorização (Spring Security + JWT).
