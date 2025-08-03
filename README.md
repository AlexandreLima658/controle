# 📌 Controle de Custos Mensais

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-Build-blue?logo=gradle)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-lightgrey)

> Sistema para controle de despesas mensais, desenvolvido com **Java + Spring Boot**, aplicando **Domain-Driven Design (DDD)** e **Clean Architecture** para uma arquitetura modular, escalável e de fácil manutenção.

---

## 🚀 Tecnologias utilizadas

- **Java 17+**
- **Spring Boot (Web, Validation, Swagger)**
- **JPA (Java Persistence API)** para persistência de dados
- **PostgreSQL** (Banco de dados relacional)
- **Docker & Docker Compose** (Ambiente de desenvolvimento)
- **Arquitetura:** DDD + Clean Architecture
- **Testes:** JUnit 5 + Mockito
- **Swagger** (Documentação da API)
- **Gradle** (Gestão de dependências e build)

---

## 🏗️ Estrutura do Projeto

A aplicação segue os princípios de **DDD** e **Clean Architecture**, dividida em camadas:
    
    📂 src
     ┣ 📂 application → Casos de uso (regras de aplicação)
     ┣ 📂 domain → Entidades, agregados, objetos de valores e regras de negócio
     ┣ 📂 infrastructure → Persistência (JPA), controllers REST, configurações
     


## ⚙️ Configuração do ambiente

### 1️⃣ Clonar o repositório

```bash
  git clone https://github.com/AlexandreLima658/controle.git
```

### 2️⃣ Criar variáveis de ambiente

    DATABASE_USER=example_user
    DATABASE_PASSWORD=example_password
    DATABASE_URL=example_url

### 3️⃣ Subir o banco de dados com o Docker

    cd docker
    docker compose up -d

### 4️⃣ Acessar a documentação da API

    http://localhost:8080/swagger-ui/index.html

✅ Funcionalidades

- ✅ Cadastro de usuários
- ✅ Cadastro e gerenciamento de categorias
- ✅ Registro de despesas mensais
- ✅ Listagem e filtragem de despesas
- ✅ API documentada com Swagger


## 📌 Endpoints da API

### 🗂️ Categories
| Método | Endpoint                      | Descrição                          |
|--------|------------------------------|------------------------------------|
| GET    | `/categories/{categoryId}`   | Retorna uma categoria pelo ID      |
| PUT    | `/categories/{categoryId}`   | Atualiza uma categoria pelo ID     |
| DELETE | `/categories/{categoryId}`   | Remove uma categoria pelo ID       |
| GET    | `/categories`                | Lista todas as categorias          |
| POST   | `/categories`                | Cria uma nova categoria            |

---

### 💰 Expenses
| Método | Endpoint                      | Descrição                          |
|--------|------------------------------|------------------------------------|
| GET    | `/expenses/{expenseId}`      | Retorna uma despesa pelo ID        |
| PUT    | `/expenses/{expenseId}`      | Atualiza uma despesa pelo ID       |
| DELETE | `/expenses/{expenseId}`      | Remove uma despesa pelo ID         |
| GET    | `/expenses`                  | Lista todas as despesas            |
| POST   | `/expenses`                  | Cria uma nova despesa              |

---

### 💳 Payments
| Método | Endpoint                      | Descrição                          |
|--------|------------------------------|------------------------------------|
| POST   | `/payments`                   | Cria um novo pagamento             |

---

### 👤 Users
| Método | Endpoint                      | Descrição                          |
|--------|------------------------------|------------------------------------|
| GET    | `/users/{userId}`            | Retorna um usuário pelo ID         |
| PUT    | `/users/{userId}`            | Atualiza um usuário pelo ID        |
| DELETE | `/users/{userId}`            | Remove um usuário pelo ID          |
| GET    | `/users`                     | Lista todos os usuários            |
| POST   | `/users`                     | Cria um novo usuário               |



### 🤝 Contribuindo

    Faça um fork do projeto
    
    Crie uma branch (feature/minha-feature)
    
    Commit suas alterações (git commit -m 'Adiciona minha feature')
    
    Faça push para a branch (git push origin feature/minha-feature)
    
    Abra um Pull Request