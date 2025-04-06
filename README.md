# 📝 API de Gestão de Tarefas

## 📌 Introdução

Olá! Esta é uma **API RESTful** para a gestão de tarefas do tipo *To-Do*.

Ela foi desenvolvida com as seguintes tecnologias:

- ☕ **Java 17**
- 🌱 **Spring Boot 3**
- 📄 **Documentação Swagger**
- ☁️ **Deploy em nuvem via plataforma Railway**

  ## 🌐 Acesso ao Projeto

Para acessar o projeto, copie e cole o seguinte endereço em uma nova aba do seu navegador:

🔗 [https://gestaodetarefas.up.railway.app/swagger-ui/index.html](https://gestaodetarefas.up.railway.app/swagger-ui/index.html)

A partir dessa URL, você será redirecionado à **documentação interativa Swagger**, onde poderá testar os endpoints disponíveis da API diretamente pelo navegador.



## Instruções de Utilização
Para executar o projeto:
1. Clone este repositório em seu PC.
2. Abra o projeto na IDE de sua preferência.
3. Utilize o [Postman](https://www.postman.com) para acessar os endpoints e interagir com o programa.

## Descrição
A API permite aos usuários realizar as seguintes operações:
- Cadastrar uma nova tarefa.
- Listar todas as tarefas.
- Buscar uma tarefa por ID.
- Atualizar o status de uma tarefa.
- Excluir uma tarefa.

---

## Modelo de Dados
`Tarefa` é a entidade principal do sistema e possui os seguintes campos:
- **id**: Identificador único da tarefa (autogerado).
- **título**: Título da tarefa (obrigatório).
- **descrição**: Descrição da tarefa (opcional).
- **status**: Status da tarefa (ex: "Pendente", "Em andamento", "Concluída").

---

## Endpoints da API
A API expõe os seguintes endpoints:

### Criar uma nova tarefa
**POST** `localhost:8080/api/tarefas`

**Exemplo de entrada JSON:**
```json
{
  "titulo": "Garagem",
  "descricao": "Arrumar a bagunça.",
  "status": "Pendente"
}
```

### Retornar todas as tarefas
**GET** `localhost:8080/api/tarefas`

### Buscar uma tarefa por ID
**GET** `localhost:8080/api/tarefas/{id}`

### Atualizar o status de uma tarefa existente
**PUT** `localhost:8080/api/tarefas/{id}`

**Exemplo de entrada JSON:**
```json
{
  "status": "Pendente"
}
```

### Excluir uma tarefa
**DELETE** `localhost:8080/api/tarefas/{id}`

---

## Tecnologias Utilizadas
- **Spring Boot**
- **Spring Web**: Criação da API RESTful.
- **Spring Data JPA**: Persistência de dados no banco de dados relacional H2.
- **JUnit**: Testes unitários.
- **H2 Database**: Banco de dados em memória para simplificação.

---

## Testes
1. **JUnit** para testar os métodos do serviço `TarefaService` e os endpoints da API.
2. Testes cobrindo cada operação (criação, listagem, atualização, exclusão).

---

## Boas Práticas Seguidas
- **Clean Code**: Código organizado, legível e modular.
- **Testes**: Todos os cenários possíveis são testados.
- **Validação de Dados**: Validações nas entradas da API (ex: título obrigatório).

---

## Obrigado!

