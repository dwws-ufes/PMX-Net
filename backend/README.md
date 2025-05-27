# PMX-Net (Spring Boot Backend)

Este é o backend do sistema PMX-Net, desenvolvido com Spring Boot e banco de dados SQLite.

## 🧰 Tecnologias

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- SQLite
- Maven

## ▶️ Como executar

1. Compile e rode a aplicação:

```bash
mvn clean install
mvn spring-boot:run
```

2. Acesse a API (exemplo):

- [http://localhost:8080/api/usuarios](http://localhost:8080/api/usuarios)

## 📂 Banco de dados

O sistema utiliza SQLite. O arquivo `pmxnet.db` será criado automaticamente no diretório raiz ao executar a aplicação.

## 📌 Observações

- A aplicação é *stateless* e utiliza repository personalizados.
- Toda estrutura segue o padrão arquitetural FrameWeb.


## 📘 Documentação da API

Após iniciar a aplicação, acesse a documentação Swagger em:

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## 🔐 Autenticação

Faça login com:

```
POST /api/auth/login
Body:
{
  "email": "usuario@ufes.edu.br",
  "senha": "123456"
}
```

Resposta:
```json
{ "token": "JWT..." }
```

Use esse token em chamadas autenticadas no header:
```
Authorization: Bearer <token>
```
