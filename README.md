# MRV Leads API - Backend

Este projeto é uma API RESTful para gerenciamento de leads, desenvolvida em Java 21 com Spring Boot 3.3.0 e SQL Server.

## Pré-requisitos

- Java 21 (JDK)
- Maven 3.3+
- Microsoft SQL Server (local ou Docker)

## Configuração do Banco de Dados

1. **Crie o banco de dados:**
   
   No SQL Server, execute:
   ```sql
   CREATE DATABASE leadsdb;
   ```

2. **Crie a tabela:**
   ```sql
   CREATE TABLE LEAD (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    CONTACT_FIRST_NAME VARCHAR(255) NOT NULL,
    CONTACT_LAST_NAME VARCHAR(255),
    CONTACT_PHONE VARCHAR(255),
    CONTACT_EMAIL VARCHAR(255),
    DATE_CREATED DATETIME2,
    SUBURB VARCHAR(255),
    CATEGORY VARCHAR(255),
    DESCRIPTION VARCHAR(1000),
    PRICE DECIMAL(10,2),
    STATUS VARCHAR(1)
);
   ```

3. **Configure o acesso em `src/main/resources/application.properties` (senha utilizada durante desenvolvimento: MrvStr0ngPa$$woRd123):**
   
   Exemplo:
   ```properties
   spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=leadsdb;encrypt=true;trustServerCertificate=true
   spring.datasource.username=sa
   spring.datasource.password=SUA_SENHA
   spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
   ```

## Executando a API

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/rodrigojmcosta/leads-mrv-back.git
   cd leads-mrv-back
   ```

2. **Compile o projeto:**
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```
   ou
   ```bash
   java -jar target/leads-mrv-0.0.1-SNAPSHOT.jar
   ```

4. **Acesse a API:**
   - Base URL: `http://localhost:8080/api/v1/leads`
   - A API utiliza autenticação Basic Auth:
     - Usuário: `admin`
     - Senha: `admin123`

## Endpoints principais

- `GET /api/v1/leads/invited` — Lista leads convidados
- `GET /api/v1/leads/accepted` — Lista leads aceitos
- `PUT /api/v1/leads/{id}/accept` — Aceita um lead e retorna arquivo de notificação em base64
- `PUT /api/v1/leads/{id}/decline` — Recusa um lead
- `GET /api/v1/leads/{id}` — Busca lead por id

## Observações
- Ao aceitar um lead, um arquivo de notificação é gerado e retornado em base64 para download no frontend.
- O envio de e-mail é simulado e não envia e-mails reais.
- Para ambiente de desenvolvimento, o CORS está liberado para qualquer origem.