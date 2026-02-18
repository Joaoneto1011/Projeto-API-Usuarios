# 🚀 API de Gerenciamento de Usuários

API REST desenvolvida com **Spring Boot** para gerenciamento de usuários, aplicando boas práticas de arquitetura, validação, tratamento global de exceções e testes unitários.

Projeto desenvolvido com foco em **portfólio profissional**, demonstrando organização e separação de responsabilidades.

---

## 📌 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (banco em memória)
- Jakarta Validation
- Lombok
- JUnit 5
- Mockito
- Maven
- Postman para testes

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:

controller → service → repository → database

---

### 📂 Estrutura de Pacotes

com.projetoapi
│
├── controllers
│ └── UsuarioController
│
├── services
│ └── UsuarioService
│
├── repositorios
│ └── UsuarioRepository
│
├── dominio
│ ├── Usuario
│ └── Role (Enum)
│
├── dto
│ ├── UsuarioRequestDTO
│ └── UsuarioResponseDTO
│
├── excecoes
│ ├── UsuarioNaoEncontradoException
│ ├── EmailJaCadastradoException
│ ├── RespostaDeErro
│ └── ManipuladorDeExcecoesGlobal
│
└── ProjetoapiApplication

---


### 🔎 Responsabilidade de cada camada

- **Controller** → Recebe requisições HTTP e retorna respostas.
- **Service** → Contém regras de negócio.
- **Repository** → Comunicação com o banco de dados.
- **DTO** → Controla dados de entrada e saída.
- **Exceções** → Tratamento global padronizado de erros.

---

## 👤 Funcionalidades da API

- ✅ Cadastrar usuário
- ✅ Listar todos usuários
- ✅ Buscar usuário por ID
- ✅ Buscar usuário por email
- ✅ Atualizar usuário
- ✅ Deletar usuário
- ✅ Validação de campos
- ✅ Validação de email duplicado
- ✅ Tratamento global de erros

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

🔐 Regras aplicadas

Email único

Senha obrigatória (6 a 10 caracteres)

Role padrão: CLIENTE

Datas de criação e atualização automáticas

Senha não é retornada na resposta da API

---

❌ Padrão de Erros

A API retorna respostas padronizadas no seguinte formato:

{
  "dataHora": "2026-02-17T18:00:00",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Usuario não encontrado.",
  "caminho": "/usuarios/10"
}

Tratamentos implementados:

400 → Erro de validação

404 → Usuário não encontrado

409 → Email já cadastrado

500 → Erro interno inesperado

---


