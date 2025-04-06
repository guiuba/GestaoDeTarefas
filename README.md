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
```
https://gestaodetarefas.up.railway.app/swagger-ui/index.html
```

A partir dessa URL, você será redirecionado à **documentação interativa Swagger**, onde poderá testar os endpoints disponíveis da API diretamente pelo navegador:

![image](https://github.com/user-attachments/assets/db152eb7-3ec2-4f94-85fe-fa57a9958715)

## 📖 Descrição

A API permite aos usuários realizar as seguintes operações:

- ✅ **Cadastrar uma nova tarefa**
- 📋 **Listar todas as tarefas**
- 🔍 **Buscar uma tarefa por ID**
- 🔄 **Atualizar o status de uma tarefa**
- ❌ **Excluir uma tarefa**

---

## 🗂️ Modelo de Dados

**Tarefa** é a entidade principal do sistema e possui os seguintes campos:

- 🆔 `id`: Identificador único da tarefa *(autogerado)*  
- 📝 `titulo`: Título da tarefa *(obrigatório)*  
- 📄 `descricao`: Descrição da tarefa *(opcional)*  
- 🚦 `status`: Status da tarefa  
 
**Exemplo de entrada no formato JSON para a criação de uma nova tarefa:**
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

