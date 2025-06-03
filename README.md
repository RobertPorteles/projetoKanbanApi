# Projeto Kanban API!
 Projeto Spring Boot para controle de tarefas em estilo Kanban, com:

documentação interativa via Swagger

validação de dados com Bean Validation

persistência de dados com PostgreSQL


---

## Índice

1. [Visão Geral](#visão-geral)  
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)  
3. [Pré-requisitos](#pré-requisitos)  
4. [Estrutura de Pastas](#estrutura-de-pastas)  
5. [Configuração do Banco de Dados com Docker](#configuração-do-banco-de-dados-com-docker)  
6. [Configuração do `application.properties`](#configuração-do-applicationproperties)  
7. [Entidade JPA: TarefaKanban](#entidade-jpa-tarefakanban)  
8. [Repositório: TarefaKanbanRepository](#repositório-tarefakanbanrepository)  
9. [DTOs](#dtos)  
   - `TarefaKanbanRequestDto`  
   - `TarefaKanbanResponseDto`  
10. [Validação Bean Validation](#validação-bean-validation)  
11. [ModelMapper](#modelmapper)  
12. [Swagger / OpenAPI](#swagger--openapi)  
13. [Controller: TarefasKanbanController](#controller-tarefaskanbancontroller)  
   - Endpoints: `POST`, `PUT`, `DELETE`, `GET`  
14. [Tratamento Global de Exceções](#tratamento-global-de-exceções)  
15. [Como Rodar o Projeto](#como-rodar-o-projeto)  
16. [Testes com Postman](#testes-com-postman)  
17. [Publicação no GitHub](#publicação-no-github)  

---

## Visão Geral

Esta API RESTful foi desenvolvida em Spring Boot para gerenciar tarefas em um quadro Kanban. Cada tarefa possui campos como título, descrição, responsável, datas de criação e de entrega, além de indicadores de início e conclusão. O projeto inclui:

- Persistência em PostgreSQL  
- Validação de dados via Bean Validation  
- Conversão automática entre entidades e DTOs com ModelMapper  
- Documentação interativa com Swagger  
- Tratamento global de erros com @ControllerAdvice para respostas padronizadas em caso de falhas (como validação e recursos não encontrados)

---

## Tecnologias Utilizadas

- Java 21  
- Spring Boot (Spring Web, Spring Data JPA, Bean Validation, DevTools)  
- Maven  
- PostgreSQL (rodando em Docker)  
- Lombok  
- ModelMapper  
- SpringDoc OpenAPI (Swagger UI)  

---

## Pré-requisitos

- Java 21 instalado  
- Maven instalado  
- Docker (para rodar o banco PostgreSQL)  
- Conta no GitHub (para clonar ou publicar o repositório)  

---

## Estrutura de Pastas

\`\`\`
projetoKanbanApi/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ br.com.kanbanboard/
│  │  │     ├─ controllers/        
│  │  │     ├─ configurations/     
│  │  │     ├─ dtos/               
│  │  │     ├─ entities/           
│  │  │     ├─ handlers/           
│  │  │     └─ repositories/       
│  │  └─ resources/
│  │     ├─ application.properties 
│  │     └─ ...
│  └─ test/                         
├─ docker-compose.yml              
└─ pom.xml                         
\`\`\`

---

## Configuração do Banco de Dados com Docker

1. Crie um arquivo \`docker-compose.yml\` contendo a definição de um serviço PostgreSQL que inclui:
   - Imagem do PostgreSQL  
   - Mapeamento de porta  
   - Variáveis de ambiente para nome do banco, usuário e senha  
   - Volume dedicado para persistência de dados  
   - Política de reinício  

2. Execute o comando \`docker-compose up -d\` para iniciar o container.  
3. Verifique se o serviço está ativo usando \`docker ps\`.  
4. (Opcional) Conecte-se via ferramenta de sua preferência ou linha de comando, usando as credenciais definidas.  

---

## Configuração do \`application.properties\`

Localizado em \`src/main/resources\`, esse arquivo deve conter:

- A porta em que o Spring Boot será executado (8084).  
- A URL de conexão com o PostgreSQL (hostname, porta, nome do banco).  
- Usuário e senha do banco.  
- Dialeto do Hibernate para PostgreSQL e estratégia de geração de esquema.  
- Habilitação de exibição de SQL no console.  
- (Opcional) Ajustes de caminhos para o Swagger UI e o endpoint \`/v3/api-docs\`.

---

## Entidade JPA: TarefaKanban

A classe \`TarefaKanban\` representa a tabela \`tarefa_kanban\` e possui campos para:

- UUID autogerado como identificador  
- Título, descrição e responsável (strings com tamanhos definidos)  
- Data de criação (definida pelo servidor)  
- Data prevista de entrega  
- Banderas booleanas de iniciado e finalizado  

Cada campo é mapeado como coluna no banco usando anotações JPA, garantindo que o Hibernate consiga criar/atualizar a tabela automaticamente.

---

## Repositório: TarefaKanbanRepository

Uma interface que estende \`JpaRepository<TarefaKanban, UUID>\`, disponibilizando métodos prontos para:

- Salvar ou atualizar entidades  
- Buscar por ID  
- Listar todas as entidades  
- Deletar por ID  

---

## DTOs

### TarefaKanbanRequestDto

- Campos: título, descrição, responsável, dataPrevistaEntrega, iniciado, finalizado.  
- Anotações de Bean Validation garantem:  
  - Título: não vazio, entre 8 e 50 caracteres.  
  - Descrição: não vazia, entre 8 e 150 caracteres.  
  - Responsável: não vazio, entre 8 e 25 caracteres.  
  - DataPrevistaEntrega: string no formato \`YYYY-MM-DD\`.  
  - Iniciado e Finalizado: não nulos.

### TarefaKanbanResponseDto

- Campos: id, título, descrição, responsável, dataCriacao, dataPrevistaEntrega, iniciado, finalizado.  
- Datas formatadas em ISO8601 (com padrão UTC) usando anotações do Jackson.

---

## Validação Bean Validation

- O projeto inclui a dependência de Bean Validation do Spring Boot.  
- As anotações em \`TarefaKanbanRequestDto\` garantem que qualquer requisição que não cumpra os requisitos gere um erro **400 BAD REQUEST** contendo detalhes sobre quais campos falharam.

---

## ModelMapper

- Um bean \`ModelMapper\` é configurado via classe de configuração.  
- Permite converter automaticamente:
  - \`TarefaKanbanRequestDto\` → \`TarefaKanban\`  
  - \`TarefaKanban\` → \`TarefaKanbanResponseDto\`  

Isso evita cópias manuais de cada campo no controller.

---

## Swagger / OpenAPI

- A dependência SpringDoc OpenAPI fornece Swagger UI.  
- Por padrão, ao iniciar a aplicação, a documentação estará disponível em:
  \`\`\`
  http://localhost:8084/swagger-ui.html
  \`\`\`
- É possível customizar título, versão e descrição da API criando uma classe de configuração com bean \`OpenAPI\`, mas isso é opcional.

---

## Controller: TarefasKanbanController

Localizada em \`controllers\`, essa classe:

1. Injeta o repositório e o \`ModelMapper\` via constructor injection.  
2. Define as seguintes rotas:

   - **POST /api/v1/kanban/tarefas**  
     - Recebe \`TarefaKanbanRequestDto\`.  
     - Valida entradas.  
     - Converte DTO em entidade, define data de criação e persiste.  
     - Retorna \`TarefaKanbanResponseDto\` com **200 OK**.

   - **PUT  /api/v1/kanban/tarefas/{id}**  
     - Atualiza tarefa existente. Retorna **200 OK** ou **404 Not Found**.

   - **DELETE /api/v1/kanban/tarefas/{id}**  
     - Remove tarefa. Retorna **204 No Content** ou **404 Not Found**.

   - **GET /api/v1/kanban/tarefas**  
     - Lista todas as tarefas. Retorna **200 OK** + lista de DTOs.

---

## Tratamento Global de Exceções

A classe \`GlobalExceptionHandler\` captura falhas de validação (\`MethodArgumentNotValidException\`) e retorna um JSON padronizado com \`campo: mensagem\` além do status **400 BAD REQUEST**.

---

## Como Rodar o Projeto

1. **Clonar ou baixar o repositório**.  
2. **Iniciar o PostgreSQL** via Docker:
   - Executar \`docker-compose up -d\` na raiz do projeto.  
3. **Configurar credenciais** (usuário/senha/porta) no \`application.properties\`, se necessário.  
4. **Compilar e rodar a aplicação**:
   - \`mvn clean install\`  
   - \`mvn spring-boot:run\`  
5. **Acessar a API** em \`http://localhost:8084\`.  
6. **Abrir o Swagger UI** em \`http://localhost:8084/swagger-ui.html\`.

---

## Testes com Postman

- Importe a especificação OpenAPI (JSON em \`/v3/api-docs\`).  
- Teste cada endpoint:  
  1. **POST**: criar tarefa.  
  2. **GET**: listar tarefas.  
  3. **PUT**: editar tarefa.  
  4. **DELETE**: excluir tarefa.  
- Verifique códigos HTTP:
  - **200 / 201** para sucesso.  
  - **400** para dados inválidos.  
  - **404** para não encontrado.  
  - **204** para exclusão.

---

## Considerações Finais

- Para este projeto foi Utilizado o Material da AULA 10 e 11 Da Coti Informatica - Professor Sérgio Mendes.
- Feito por Robert Porteles

"""


