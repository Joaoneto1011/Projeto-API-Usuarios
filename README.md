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

Controller

   ↓
   
Service

   ↓
   
Repository

   ↓
   
Database (H2)

---

### 📂 Estrutura de Pacotes

```
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

## ⭐ Diferenciais Técnicos

- Arquitetura em camadas bem definida  
- Uso de DTO para proteger a entidade  
- Tratamento global de exceções com `@RestControllerAdvice`  
- Validação com Jakarta Bean Validation  
- Uso de `Optional` corretamente  
- Testes unitários isolando camada de Service  
- Regra de negócio para evitar email duplicado  
- Datas automáticas com `@PrePersist`  
- Enum para controle de tipo de usuário  

---

## 🌐 Endpoints

### 🔹 Listar usuários
```http
GET /usuarios
```

### 🔹 Buscar por ID
```http
GET /usuarios/{id}
```

### 🔹 Buscar por email
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

### 🔹 Deletar usuário
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

## 🔐 Regras Aplicadas

- Email único  
- Senha obrigatória (6 a 10 caracteres)  
- Role padrão: CLIENTE  
- Datas de criação e atualização automáticas  
- Senha não é retornada na resposta da API  

---

## ❌ Padrão de Erros

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

### Tratamentos implementados:

- 400 → Erro de validação  
- 404 → Usuário não encontrado  
- 409 → Email já cadastrado  
- 500 → Erro interno inesperado  

---

## ▶️ Como Executar o Projeto Localmente

### 🔄 1. Clonar o Repositório

```bash
git clone https://github.com/Joaoneto1011/Projeto-API-Usuarios.git
cd Projeto-API-Usuarios
```

### 🚀 2. Executar a Aplicação

#### Opção 1 — Pela IDE (IntelliJ / Eclipse)

- Abra o projeto como projeto Maven  
- Localize a classe `ProjetoapiApplication`  
- Clique em **Run**

#### Opção 2 — Pelo terminal

```bash
mvn spring-boot:run
```

---

### 🌐 3. Acessar a API

Após iniciar, a aplicação estará disponível em:

```
http://localhost:8080
```

Exemplo:

```http
GET http://localhost:8080/usuarios
```

---

### 🗄️ 4. Acessar o Banco H2 (Console Web)

O banco é em memória e pode ser acessado pelo navegador:

```
http://localhost:8080/h2-console
```

Configurações:

- JDBC URL: `jdbc:h2:mem:testdb`
- User: `sa`
- Password: (deixe vazio)

---

## 👨‍💻 Autor

**João Neto**  
Telefone: 34 99891-6565
Desenvolvedor Backend Java em formação  
Focado em Spring Boot, arquitetura limpa e boas práticas.


