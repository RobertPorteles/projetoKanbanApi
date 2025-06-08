
# Projeto API de Kanban

Este é um projeto de API REST para gerenciamento de tarefas Kanban, desenvolvido com Spring Boot, PostgreSQL e documentação automatizada com Swagger.

## ✨ Tecnologias Utilizadas

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white" />
</p>

---

## 🚀 Funcionalidades da API

- Cadastro de tarefas
- Consulta de tarefas
- Atualização de tarefas
- Remoção de tarefas
- Validação de dados (título, descrição, responsável, data prevista, status)
- Documentação interativa com Swagger UI
- Configuração de banco de dados PostgreSQL com JPA

---

## 🗂️ Estrutura do Projeto

```
src/main/java/br/com/kanbanapi
├── controllers
├── dtos
├── entities
├── interfaces
├── services
├── configurations
└── handlers
```

---

## ⚙️ Executando o Projeto

### 1️⃣ Pré-requisitos

- Java 17+ instalado
- Maven instalado
- Docker instalado (para usar o docker-compose com PostgreSQL)

### 2️⃣ Clone o projeto

```bash
git clone https://github.com/seu-usuario/projetoKanbanApi.git
cd projetoKanbanApi
```

### 3️⃣ Configure o `application.properties` (já vem configurado)

```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:8084/kanban
spring.datasource.username=kanban_user
spring.datasource.password=kanban_pass

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4️⃣ Suba o banco de dados com Docker

```bash
docker-compose up -d
```

### 5️⃣ Execute a aplicação

```bash
./mvnw spring-boot:run
```

ou

```bash
mvn spring-boot:run
```

---

## 🖥️ Acessando a API

- API Base URL:

```
http://localhost:8080/api/v1/tarefas
```

- Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📚 Exemplos de Endpoints

### POST /api/v1/tarefas

```json
{
  "titulo": "Implementar autenticação",
  "descricao": "Adicionar autenticação com JWT",
  "responsavel": "Ana Souza",
  "dataPrevistaEntrega": "2025-07-10",
  "iniciado": true,
  "finalizado": false
}
```

### GET /api/v1/tarefas

Lista todas as tarefas.

### GET /api/v1/tarefas/{id}

Consulta uma tarefa pelo ID.

### PUT /api/v1/tarefas/{id}

Atualiza os dados de uma tarefa.

### DELETE /api/v1/tarefas/{id}

Remove uma tarefa.

---

## ✅ Validações de Dados

- `titulo`: obrigatório, mínimo de 8 e máximo de 50 caracteres.
- `descricao`: obrigatório, mínimo de 8 e máximo de 150 caracteres.
- `responsavel`: obrigatório, mínimo de 8 e máximo de 25 caracteres.
- `dataPrevistaEntrega`: obrigatório, formato `YYYY-MM-DD`.
- `iniciado`: obrigatório (booleano).
- `finalizado`: obrigatório (booleano).

---

## 📄 Licença

Este projeto é de uso livre para fins de estudo e aprendizado.

---

## 🙋‍♂️ Autor

Robert Porteles  
[LinkedIn](https://www.linkedin.com/in/robert-porteles/)  
[Email](mailto:robertporteless@gmail.com)

---

## 🔗 Referências

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)  
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)  
- [SpringDoc OpenAPI](https://springdoc.org/)  
- [Docker Documentation](https://docs.docker.com/)  

---

🚀 **Boas práticas**: Este projeto segue uma arquitetura RESTful com DTOs, camada de serviço e validação global de exceções.

---

## 📌 Observações

Caso você deseje fazer deploy em produção, recomenda-se:

- Configurar variáveis de ambiente para a conexão com PostgreSQL
- Implementar autenticação/autorização (Spring Security + JWT)
- Criar testes unitários e de integração

---
