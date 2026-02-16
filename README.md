# 📅 Agendamento CRUD

Uma aplicação **REST API** desenvolvida com **Spring Boot** para gerenciar agendamentos de serviços. O sistema permite criar, ler, atualizar e deletar agendamentos, com validações de conflito de horários e consultas por data.

---

## 🎯 Funcionalidades

- ✅ **Criar Agendamentos** - Registrar novos agendamentos com validação de disponibilidade
- ✅ **Listar Agendamentos** - Consultar agendamentos por dia
- ✅ **Atualizar Agendamentos** - Modificar dados de agendamentos existentes
- ✅ **Deletar Agendamentos** - Remover agendamentos do sistema
- ✅ **Validação de Conflitos** - Prevenir agendamentos em horários já ocupados
- ✅ **Banco de Dados Integrado** - H2 Database para persistência

---

## 🛠️ Stack Tecnológico

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 25 | Linguagem de programação |
| **Spring Boot** | 4.0.2 | Framework web e REST |
| **Spring Data JPA** | - | ORM para persistência |
| **Lombok** | - | Redução de boilerplate |
| **H2 Database** | - | Banco de dados em memória |
| **Maven** | - | Gerenciador de dependências |

---

## 📋 Pré-requisitos

- **Java 25** ou superior
- **Maven 3.6+**
- Qualquer IDE (IntelliJ IDEA, Eclipse, VS Code)

---

## 🚀 Como Executar

### 1. Clone o repositório
```bash
git clone <seu-repositorio>
cd AgendamentoCRUD
```

### 2. Compile o projeto
```bash
mvn clean install
```

### 3. Execute a aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

### 4. Acesse o H2 Console (opcional)
```
http://localhost:8080/h2-console
- URL: jdbc:h2:mem:AgendamentoCRUD-db
- Usuário: IRP
- Senha: (vazio)
```

---

## 📡 Endpoints da API

### 1. Criar Agendamento
```http
POST /agendamentos
Content-Type: application/json

{
  "servico": "Corte de Cabelo",
  "dataHoraAgendamento": "2026-02-20T10:00:00",
  "profissional": "João Silva",
  "cliente": "Maria Santos",
  "telefoneCliente": "11999999999"
}
```

**Resposta (202 Accepted):**
```json
{
  "id": 1,
  "servico": "Corte de Cabelo",
  "dataHoraAgendamento": "2026-02-20T10:00:00",
  "profissional": "João Silva",
  "cliente": "Maria Santos",
  "telefoneCliente": "11999999999",
  "dataAgendamento": "2026-02-16"
}
```

### 2. Listar Agendamentos por Data
```http
GET /agendamentos?data=2026-02-20
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "servico": "Corte de Cabelo",
    "dataHoraAgendamento": "2026-02-20T10:00:00",
    "profissional": "João Silva",
    "cliente": "Maria Santos",
    "telefoneCliente": "11999999999",
    "dataAgendamento": "2026-02-16"
  }
]
```

### 3. Atualizar Agendamento
```http
PUT /agendamentos?cliente=Maria%20Santos&dataHoraAgendamento=2026-02-20T10:00:00
Content-Type: application/json

{
  "servico": "Corte e Coloração",
  "dataHoraAgendamento": "2026-02-20T14:00:00",
  "profissional": "João Silva",
  "cliente": "Maria Santos",
  "telefoneCliente": "11999999999"
}
```

**Resposta (202 Accepted):**
```json
{
  "id": 1,
  "servico": "Corte e Coloração",
  "dataHoraAgendamento": "2026-02-20T14:00:00",
  "profissional": "João Silva",
  "cliente": "Maria Santos",
  "telefoneCliente": "11999999999",
  "dataAgendamento": "2026-02-16"
}
```

### 4. Deletar Agendamento
```http
DELETE /agendamentos?cliente=Maria%20Santos&dataHoraAgendamento=2026-02-20T10:00:00
```

**Resposta (204 No Content):**
```
(sem conteúdo)
```

---

## 📊 Modelo de Dados

### Entidade: Agendamento

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | Long | Identificador único (auto-incrementado) |
| `servico` | String | Nome do serviço a ser prestado |
| `dataHoraAgendamento` | LocalDateTime | Data e hora do agendamento |
| `profissional` | String | Nome do profissional responsável |
| `cliente` | String | Nome do cliente |
| `telefoneCliente` | String | Telefone de contato do cliente |
| `dataAgendamento` | LocalDate | Data em que o agendamento foi registrado |

---

## 🏗️ Arquitetura do Projeto

```
AgendamentoCRUD/
├── src/main/java/com/IRP/AgendamentoCRUD/
│   ├── AgendamentoCrudApplication.java      (Classe principal)
│   ├── controller/
│   │   └── AgendamentoController.java       (Endpoints REST)
│   ├── infrastructure/
│   │   ├── entity/
│   │   │   └── Agendamento.java            (Entidade JPA)
│   │   └── repository/
│   │       └── AgendamentoRepository.java  (Data Access Layer)
│   └── services/
│       └── AgendamentoService.java         (Lógica de negócio)
├── src/main/resources/
│   └── application.properties               (Configurações)
└── pom.xml                                  (Dependências Maven)
```

---

## 🔍 Validações Implementadas

### Conflito de Horários
O sistema evita duplos agendamentos verificando se existe um agendamento do mesmo serviço no intervalo de 1 hora:

```java
// Se houver agendamento entre a hora solicitada e +1 hora
// o sistema lança uma RuntimeException: "Horário ja preenchido"
```

### Atualização de Agendamento
- O sistema valida se o agendamento que se deseja atualizar existe
- Impede alterações para horários já ocupados

---

## 🐛 Tratamento de Erros

| Status | Cenário |
|--------|---------|
| `202 Accepted` | Agendamento criado/atualizado com sucesso |
| `204 No Content` | Agendamento deletado com sucesso |
| `200 OK` | Lista de agendamentos retornada |
| `400 Bad Request` | Dados inválidos ou horário já preenchido |
| `500 Internal Server Error` | Erro no servidor |

---

## 📝 Notas Importantes

1. **Banco de Dados**: O H2 Database é um banco em memória, portanto **os dados serão perdidos** ao reiniciar a aplicação
2. **Validação de Conflitos**: O sistema verifica disponibilidade considerando 1 hora de duração por agendamento
3. **Formato de Data**: Use o padrão ISO-8601 para datas: `YYYY-MM-DDTHH:MM:SS`
4. **Fusos Horários**: A aplicação opera em horário local (sem suporte a múltiplos fusos)

---

## 🔄 Próximas Melhorias

- [ ] Implementar validação de entrada com `@Valid`
- [ ] Adicionar autenticação e autorização
- [ ] Migrar para banco de dados persistente (PostgreSQL/MySQL)
- [ ] Implementar tratamento de exceções customizado
- [ ] Adicionar testes unitários e de integração
- [ ] Implementar paginação na listagem
- [ ] Adicionar suporte a múltiplos profissionais por serviço
- [ ] Criar testes com Postman/Swagger

---

## 📄 Licença

Este projeto está disponível sob a licença livre. Veja o arquivo `LICENSE` para mais detalhes.

