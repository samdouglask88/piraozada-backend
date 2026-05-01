# 🍗 Piraozada — Back-end

API REST do projeto **Piraozada**, desenvolvida com Java 17 e Spring Boot 3.5.  
Responsável pela autenticação, gestão de dados e regras de negócio da aplicação.

---

## 🚀 Tecnologias

| Tecnologia | Versão | Descrição |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.5.13 | Framework principal |
| Spring Security | — | Autenticação e autorização |
| Spring Data JPA | — | Persistência de dados (ORM) |
| PostgreSQL | — | Banco de dados relacional |
| JWT (jjwt) | 0.12.6 | Geração e validação de tokens |
| Lombok | — | Redução de boilerplate |
| Apache POI | 5.2.3 | Geração/leitura de arquivos Excel |
| Maven | — | Gerenciamento de dependências |

---

## 📋 Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- [PostgreSQL 14+](https://www.postgresql.org/)

---

## ⚙️ Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/samdouglask88/piraozada-backend.git
cd piraozada-backend
```

### 2. Configure o banco de dados

Crie um banco de dados no PostgreSQL:

```sql
CREATE DATABASE piraozada;
```

### 3. Configure as variáveis de ambiente

Edite o arquivo `src/main/resources/application.properties` com os dados do seu ambiente:

```properties
# Banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/piraozada
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
jwt.secret=sua_chave_secreta_aqui
jwt.expiration=86400000
```

---

## ▶️ Como executar

### Com Maven Wrapper (recomendado)

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Com Maven instalado

```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 🏗️ Estrutura do Projeto
```
piraozada-backend/
├── src/
│   ├── main/
│   │   ├── java/com/piraozada/
│   │   │   ├── config/          # Configurações (Security, CORS, JWT)
│   │   │   ├── controller/      # Controllers REST
│   │   │   ├── domain/          # Entidades JPA e enums
│   │   │   ├── dto/             # Objetos de transferência de dados
│   │   │   ├── repository/      # Repositórios JPA
│   │   │   ├── service/         # Regras de negócio
│   │   │   └── PiraozadaApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .gitignore
├── mvnw / mvnw.cmd
├── pom.xml
└── README.md
```
---

## 🔐 Autenticação

A API utiliza **JWT (JSON Web Token)** para autenticação.

Para acessar endpoints protegidos, inclua o token no header da requisição:
```
Authorization: Bearer <seu_token_jwt>
```
---

## 🛠️ Build para produção

```bash
# Gerar o JAR
./mvnw clean package -DskipTests

# Executar o JAR gerado
java -jar target/piraozada-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Autor

Desenvolvido por **[samdouglask88](https://github.com/samdouglask88)**
