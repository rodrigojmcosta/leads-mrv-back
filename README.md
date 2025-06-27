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

3. **Popule a tabela com dados para teste:**
```sql
INSERT INTO LEAD (CONTACT_FIRST_NAME, CONTACT_LAST_NAME, CONTACT_PHONE, CONTACT_EMAIL, DATE_CREATED, SUBURB, CATEGORY, DESCRIPTION, PRICE, STATUS)
VALUES

-- 5 INVITED LEADS
('Ana', 'Silva', '11999990001', 'ana.silva@email.com', GETDATE(), 'Centro', 'Reforma', 'Reforma de apartamento', 800.50, 'I'),
('Bruno', 'Souza', '11999990002', 'bruno.souza@email.com', GETDATE(), 'Jardins', 'Pintura', 'Pintura residencial', 300.00, 'I'),
('Carla', 'Oliveira', '11999990003', 'carla.oliveira@email.com', GETDATE(), 'Moema', 'Elétrica', 'Revisão elétrica', 450.00, 'I'),
('Daniel', 'Lima', '11999990004', 'daniel.lima@email.com', GETDATE(), 'Pinheiros', 'Hidráulica', 'Conserto hidráulico', 200.00, 'I'),
('Barbara', 'Ferreira', '11999990005', 'barbara.ferreira@email.com', GETDATE(), 'Vila Mariana', 'Marcenaria', 'Móveis planejados', 1200.00, 'I'),

-- 5 ACCEPTED LEADS
('Felipe', 'Costa', '11999990006', 'felipe.costa@email.com', GETDATE(), 'Santana', 'Reforma', 'Aceito para reforma', 700.00, 'A'),
('Gabriela', 'Mendes', '11999990007', 'gabriela.mendes@email.com', GETDATE(), 'Tatuapé', 'Pintura', 'Aceito para pintura', 350.00, 'A'),
('Henrique', 'Alves', '11999990008', 'henrique.alves@email.com', GETDATE(), 'Itaim', 'Elétrica', 'Aceito para elétrica', 600.00, 'A'),
('Isabela', 'Ramos', '11999990009', 'isabela.ramos@email.com', GETDATE(), 'Brooklin', 'Hidráulica', 'Aceito para hidráulica', 250.00, 'A'),
('João', 'Barros', '11999990010', 'joao.barros@email.com', GETDATE(), 'Liberdade', 'Marcenaria', 'Aceito para marcenaria', 950.00, 'A'),

-- 3 DECLINED LEADS
('Karen', 'Pereira', '11999990011', 'karen.pereira@email.com', GETDATE(), 'Aclimação', 'Reforma', 'Recusado para reforma', 400.00, 'D'),
('Lucas', 'Martins', '11999990012', 'lucas.martins@email.com', GETDATE(), 'Saúde', 'Pintura', 'Recusado para pintura', 500.00, 'D'),
('Marina', 'Gomes', '11999990013', 'marina.gomes@email.com', GETDATE(), 'Bela Vista', 'Elétrica', 'Recusado para elétrica', 650.00, 'D');
```

## Executando a API

1. **Clone o repositório:**
```bash
git clone https://github.com/rodrigojmcosta/leads-mrv-back.git
cd leads-mrv-back
```

2. **Configure o acesso ao BD em `src/main/resources/application.properties` (senha utilizada durante desenvolvimento: MrvStr0ngPa$$woRd123):**
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=leadsdb;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
```

3. **Compile o projeto:**
```bash
mvn clean install
```

4. **Execute a aplicação:**
```bash
mvn spring-boot:run
```
ou
```bash
java -jar target/leads-mrv-0.0.1-SNAPSHOT.jar
```

5. **Acesse a API:**
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