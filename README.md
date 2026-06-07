# 🚀 User Management API - Spring Boot

API REST desenvolvida com Spring Boot para gerenciamento de usuários, aplicando boas práticas de arquitetura, validação, tratamento global de exceções e testes unitários.

Projeto desenvolvido com foco em portfólio profissional, demonstrando organização, separação de responsabilidades e aplicação de conceitos utilizados no mercado de desenvolvimento backend.

---

## 📈 Status do Projeto

✅ Concluído e funcional

🔄 Novas melhorias e funcionalidades poderão ser adicionadas futuramente.

---

## 📌 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* H2 Database (Banco em memória)
* Jakarta Validation
* Lombok
* JUnit 5
* Mockito
* Maven
* Postman para testes

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database (H2)
```

---

## 📂 Estrutura de Pacotes

```text
com.projetoapi
│
├── controllers
│   └── UsuarioController
│
├── services
│   └── UsuarioService
│
├── repositorios
│   └── UsuarioRepository
│
├── dominio
│   ├── Usuario
│   └── Role (Enum)
│
├── dto
│   ├── UsuarioRequestDTO
│   └── UsuarioResponseDTO
│
├── excecoes
│   ├── UsuarioNaoEncontradoException
│   ├── EmailJaCadastradoException
│   ├── RespostaDeErro
│   └── ManipuladorDeExcecoesGlobal
│
└── ProjetoapiApplication
```

---

## 🔎 Responsabilidade de Cada Camada

### Controller

Recebe requisições HTTP e retorna respostas para o cliente.

### Service

Contém as regras de negócio da aplicação.

### Repository

Responsável pela comunicação com o banco de dados.

### DTO

Controla os dados de entrada e saída da API.

### Exceções

Padroniza e centraliza o tratamento de erros da aplicação.

---

## 👤 Funcionalidades da API

✅ Cadastrar usuário

✅ Listar todos os usuários

✅ Buscar usuário por ID

✅ Buscar usuário por e-mail

✅ Atualizar usuário

✅ Excluir usuário

✅ Validação de campos obrigatórios

✅ Validação de e-mail duplicado

✅ Tratamento global de exceções

---

## ⭐ Diferenciais Técnicos

* Arquitetura em camadas bem definida
* Uso de DTOs para proteger a entidade
* Tratamento global de exceções com `@RestControllerAdvice`
* Validação utilizando Jakarta Bean Validation
* Uso correto de `Optional`
* Testes unitários isolando a camada de Service
* Regra de negócio para evitar e-mails duplicados
* Datas automáticas utilizando `@PrePersist`
* Enum para controle de tipos de usuário

---

## 🌐 Endpoints

### 🔹 Listar usuários

```http
GET /usuarios
```

### 🔹 Buscar usuário por ID

```http
GET /usuarios/{id}
```

### 🔹 Buscar usuário por e-mail

```http
GET /usuarios/email/{email}
```

### 🔹 Criar usuário

```http
POST /usuarios
```

Body:

```json
{
  "nome": "João",
  "email": "joao@gmail.com",
  "senha": "123456"
}
```

### 🔹 Atualizar usuário

```http
PUT /usuarios/{id}
```

### 🔹 Excluir usuário

```http
DELETE /usuarios/{id}
```

---

## 📌 Modelo de Usuário

```json
{
  "id": 1,
  "nome": "João",
  "email": "joao@gmail.com",
  "role": "CLIENTE",
  "dataCriacao": "2026-02-17T17:30:00"
}
```

---

## 🔐 Regras Aplicadas

* E-mail único no sistema
* Senha obrigatória (6 a 10 caracteres)
* Role padrão: CLIENTE
* Datas de criação e atualização automáticas
* Senha não é retornada nas respostas da API

---

## 🛡️ Validações

Validações implementadas utilizando Jakarta Validation:

* Nome obrigatório
* E-mail obrigatório
* Formato válido de e-mail
* Senha obrigatória
* Limite mínimo e máximo de caracteres para senha

---

## 🧪 Testes Unitários

O projeto possui testes unitários na camada de serviço utilizando:

* JUnit 5
* Mockito

Os testes validam:

* Cadastro de usuários
* Busca por ID
* Busca por e-mail
* Atualização de usuários
* Exclusão de usuários
* Regras de e-mail duplicado

---

## ❌ Padrão de Resposta de Erro

A API retorna respostas padronizadas no seguinte formato:

```json
{
  "dataHora": "2026-02-17T18:00:00",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Usuario não encontrado.",
  "caminho": "/usuarios/10"
}
```

### Tratamentos implementados

| Status | Descrição               |
| ------ | ----------------------- |
| 400    | Erro de validação       |
| 404    | Usuário não encontrado  |
| 409    | E-mail já cadastrado    |
| 500    | Erro interno inesperado |

---

## ▶️ Como Executar o Projeto Localmente

### 🔄 1. Clonar o Repositório

```bash
git clone https://github.com/Joaoneto1011/Projeto-API-Usuarios.git
```

```bash
cd Projeto-API-Usuarios
```

### 🚀 2. Executar a Aplicação

#### Opção 1 — Pela IDE

* Abra o projeto como projeto Maven
* Localize a classe `ProjetoapiApplication`
* Clique em Run

#### Opção 2 — Pelo Terminal

```bash
mvn spring-boot:run
```

---

### 🌐 3. Acessar a API

Após iniciar a aplicação:

```text
http://localhost:8080
```

Exemplo:

```http
GET http://localhost:8080/usuarios
```

---

### 🗄️ 4. Acessar o Banco H2

O banco de dados em memória pode ser acessado pelo navegador:

```text
http://localhost:8080/h2-console
```

Configurações:

```text
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: (deixe vazio)
```

---

## 🎯 Objetivos do Projeto

Este projeto foi desenvolvido para:

* Praticar desenvolvimento de APIs REST com Spring Boot
* Aplicar arquitetura em camadas
* Utilizar DTOs para entrada e saída de dados
* Implementar validações e tratamento global de exceções
* Desenvolver testes unitários com JUnit e Mockito
* Aplicar boas práticas de desenvolvimento backend
* Compor portfólio profissional para oportunidades na área de tecnologia

---

## 👨‍💻 Autor

### João Neto

Desenvolvedor Backend Java em formação.

Focado em:

* Java
* Spring Boot
* APIs REST
* Banco de Dados
* Arquitetura de Software

GitHub:
https://github.com/Joaoneto1011

LinkedIn:
https://www.linkedin.com/in/joao-rodrigues-neto-855757293/

---

## 📌 Observação

Este projeto foi desenvolvido para fins de estudo, prática e evolução profissional, seguindo boas práticas utilizadas no desenvolvimento de APIs REST modernas com Spring Boot.
