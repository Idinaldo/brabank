## Account Service
Responsável por gerenciar as contas e informações bancárias. As principais atribuições desse microsserviço são:
- Cadastro de conta
- Atualização de conta
- Consulta de conta
- Remoção de conta
- Consulta de saldo

### Entidades
#### Account
|   Attribute    |                            Data Type                             |                          Constraints                           |
|:--------------:|:----------------------------------------------------------------:|:--------------------------------------------------------------:|
|       id       |                               uuid                               |                          PRIMARY KEY                           |
|    user_id     |                               uuid                               |                      NOT NULL UNIQUE KEY                       |
|    balance     |                            BigDecimal                            |                            NOT NULL                            |
|     status     | enum('ACTIVE', 'BLOCKED', 'DEACTIVATED', 'PENDING_VERIFICATION') |            NOT NULL DEFAULT 'PENDING_VERIFICATION'             |
| account_number |                             char(7)                              |                            NOT NULL                            |
|  branch_code   |                             char(4)                              |                            NOT NULL                            |
|   created_at   |                            timestamp                             |               NOT NULL DEFAULT CURRENT_TIMESTAMP               |
|   updated_at   |                            timestamp                             | NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |

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
