# Brabank - Fintech API

## Introdução
O Brabank é uma fintech fictícia idealizada para servir como objeto de estudo nas áreas de Engenharia de Software e Arquitetura de Software. O Brabank - Fintech API é a aplicação desse estudo e tem o objetivo ser um sistema robusto baseado em microsserviços, cloud e arquitetura hexagonal.
O sistema deve ser capaz de:
- Gerenciar credenciais (Identity) dos usuários (criar, atualizar e autenticar)
- Gerenciar contas bancárias (criar, atualizar e remover)
- Processar transações financeiras (depósitos, saques, transferências e estorno)
- Gerenciar cartões virtuais (criar, remover) 
- Realizar autenticação e autorização baseadas em JWT

## Detalhes Técnicos

### Tecnologias Principais
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Lombok
- Docker
- PostgreSQL

## Ferramentas, Metodologias e Design Patterns
Para o desenvolvimento do projeto Brabank - Fintech API utiliza-se as seguintes ferramentas, metodologias e design patterns:
- Git / GitHub
- API REST
- Clean Code
- Design Patterns
- CI/CD
- DDD (Domain-Driven Design)

## Arquitetura
O Brabank - Fintech API é baseado em arquitetura de microsserviços e arquitetura hexagonal. Dessa forma, se faz essencial a definição dos microsserviços da aplicação.

## Auth Service
Responsável por gerenciar credenciais. As principais atribuições desse microsserviço são:
- Cadastro de Identity
- Atualização de Identity
- Autenticação de Identity
- Geração de Tokens JWT

### Entidades
#### Identity
|   Attribute   |                             Data Type                            |                           Constraints                          |
|:-------------:|:----------------------------------------------------------------:|:--------------------------------------------------------------:|
|       id      |                               uuid                               |                           PRIMARY KEY                          |
|     email     |                           varchar(255)                           |                       NOT NULL UNIQUE KEY                      |
| password_hash |                             char(60)                             |                            NOT NULL                            |
|     status    | enum('ACTIVE', 'BLOCKED', 'DEACTIVATED', 'PENDING_VERIFICATION') |             NOT NULL DEFAULT 'PENDING_VERIFICATION'            |
|      role     |            enum('CLIENT', 'ADMIN', 'CUSTOMER_SUPPORT')           |                    NOT NULL DEFAULT 'CLIENT'                   |
|   created_at  |                             timestamp                            |               NOT NULL DEFAULT CURRENT_TIMESTAMP               |
|   updated_at  |                             timestamp                            | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |

## Account Service
Responsável por gerenciar as contas e informações bancárias. As principais atribuições desse microsserviço são:
- Cadastro de conta
- Atualização de conta
- Consulta de conta
- Remoção de conta
- Consulta de saldo

### Entidades
#### Account
|  Attribute |                             Data Type                            |                           Constraints                          |
|:----------:|:----------------------------------------------------------------:|:--------------------------------------------------------------:|
|     id     |                               uuid                               |                           PRIMARY KEY                          |
|   user_id  |                               uuid                               |                       NOT NULL UNIQUE KEY                      |
|   balance  |                            BigDecimal                            |                            NOT NULL                            |
|   status   | enum('ACTIVE', 'BLOCKED', 'DEACTIVATED', 'PENDING_VERIFICATION') |             NOT NULL DEFAULT 'PENDING_VERIFICATION'            |
| created_at |                             timestamp                            |               NOT NULL DEFAULT CURRENT_TIMESTAMP               |
| updated_at |                             timestamp                            | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |

#### User
|   Attribute  |                             Data Type                            |                           Constraints                          |
|:------------:|:----------------------------------------------------------------:|:--------------------------------------------------------------:|
|      id      |                               uuid                               |                           PRIMARY KEY                          |
|     email    |                           varchar(255)                           |                       NOT NULL UNIQUE KEY                      |
|   full_name  |                           varchar(100)                           |                            NOT NULL                            |
|  birth_date  |                               date                               |                            NOT NULL                            |
| phone_number |                            varchar(20)                           |                       NOT NULL UNIQUE KEY                      |
|      cpf     |                             char(11)                             |                       NOT NULL UNIQUE KEY                      |
|    status    | enum('ACTIVE', 'DEACTIVATED', 'BLOCKED', 'VERIFICATION_PENDING') |             NOT NULL DEFAULT 'VERIFICATION_PENDING'            |
|  created_at  |                             timestamp                            |               NOT NULL DEFAULT CURRENT_TIMESTAMP               |
|   update_at  |                             timestamp                            | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |

<p>Destaca-se que User não é Identity. São entidades diferentes com objetivos diferentes: User guarda os dados do usuário, Identity guarda as credenciais do usuário.</p>

## Transaction Service
Responsável por gerenciar transações financeiras. As principais atribuições desse microsserviço são:
- Processar depósitos
- Processar saques
- Processar os diferentes tipos de transferências (TED, DOC e Pix)
- Realizar estorno
- Gerar Receipts
<p>Receipts são documentos com os dados das transações.</p>

### Entidades
#### Transaction

|   Attribute  |                        Data Type                       |               Constraints               |
|:------------:|:------------------------------------------------------:|:---------------------------------------:|
|      id      |                          uuid                          |               PRIMARY KEY               |
|   sender_id  |                          uuid                          |                 NOT NULL                |
| receiver_id  |                          uuid                          |                 NOT NULL                |
|    amount    |                       BigDecimal                       |                 NOT NULL                |
|    status    | enum('PENDING', 'NOT ALLOWED', 'CANCELED', 'COMPLETE') | NOT NULL DEFAULT 'PENDING' |
| performed_at |                        timestamp                       |    NOT NULL DEFAULT CURRENT_TIMESTAMP   |

#### Ledger
|    Attribute   |                        Data Type                       |               Constraints               |
|:--------------:|:------------------------------------------------------:|:---------------------------------------:|
|       id       |                          uuid                          |               PRIMARY KEY               |
| transaction_id |                          uuid                          |           NOT NULL FOREIGN KEY          |
|   account_id   |                          uuid                          |           NOT NULL FOREIGN KEY          |
|      type      |                 enum('DEBIT', 'CREDIT')                |                 NOT NULL                |
|     amount     |                       BigDecimal                       |                 NOT NULL                |
|     status     | enum('PENDING', 'NOT ALLOWED', 'CANCELED', 'COMPLETE') | NOT NULL DEFAULT 'PENDING_VERIFICATION' |
|   created_at   |                        timestamp                       |    NOT NULL DEFAULT CURRENT_TIMESTAMP   |

<p>Destaca-se que o ledger é uma entidade imutável. Após sua criação, nada pode ser modificado. Isto é: o que entra na base de dados do ledger, fica na base de dados do ledger. <strong>Não há delete (nem <i>soft delete</i>).</strong> Essa entidade é essencial para auditoria.</p>