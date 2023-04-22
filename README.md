# Poggersnote

Uma API para o sistema de notas.

## FIAP - Devops & Cloud Computing 

### Checkpoint 2

---

## Endpoints
- Usuário
    - [Cadastrar](#cadastrar)
    - [Login](#login)
- Nota
    - [Registrar](#registrar)
    - [Alterar](#alterar-nota)
    - [Deletar Registro](#deletar-registro)
    - [Histórico](#histórico)

---

## Cadastrar
`POST` /api/usuario/cadastrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| nome | string | sim | é o nome do usuário, deve respeitar o Regex(^[a-zA-Z]{3,}$)
| email | string | sim | é o email do usuário, deve respeitar o ReGex(^[A-Za-z0-9+_.-]+@(.+)$)
| senha | string | sim | é a senha do usuário, deve ter no mínimo 8 caracteres


**Exemplo de corpo do request**
```js
{
	"nome": "Pedro Augusto",
	"email": "pedro.viana@gmail.com",
	"senha": "Senha123"
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Usuario cadastrado com sucesso
| 400 | Erro na requisição

---

---

## Login
`POST` /api/usuario/login

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| email | string | sim | é o email cadastrado pelo usuário
| senha | string | sim | é a senha cadastrada pelo usuário

**Exemplo de corpo do request**
```js
{
	"email": "usuario@email.com",
	"senha": "senha123"
}
```

**Exemplo de corpo do response**

| Campo | Tipo | Descrição
|:-------:|:------:|-------------
|Id | string | Id do usuario que identifica o usuário no sistema

```js
1010
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Usuario validado com sucesso
| 401 | Usuário ou Senha incorreto

---

---

## Registrar
`POST` /api/nota/{userId}/registrar

| Campo | Tipo | Obrigatório | Descrição
|:-------:|:------:|:-------------:|--
| data | LocalDate | sim | é a data que o usuário criou a nota
| titulo | String | sim | é a titulo da nota
| descricao | String | sim | é o conteudo da nota

**Exemplo de corpo do request**
```js
{
	"data": "2023-03-02",
	"titulo": "Tarefas 02/03"
  "descricao": "Ir ao mercado, comprar arroz e tomate. Buscar as crianças na escola."
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 201 | Nota cadastrada com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado

---

---

## Alterar Nota
`PUT` /api/nota/{id}

**Exemplo de corpo do request**
```js
{
	"data": "2023-03-02",
	"titulo": "Tarefas 02/03"
  "descricao": "Ir ao mercado, comprar arroz, feijão e tomate. Buscar as crianças na escola."
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Nota atualizada com sucesso
| 400 | Erro na requisição
| 404 | Nota não encontrada

---

---

## Deletar Registro
`DELETE` /api/nota/{userId}/deletar/{registroId}

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 204 | Objeto deletado com sucesso
| 404 | Usuario não encontrado
| 404 | Objeto não encontrado

---

---

## Histórico
`GET` /api/nota/{userId}/historico

| Campo | Tipo | Descrição
|:-------:|:------:|--
| registros | ArrayList<Registro> | é a lista que contém os as notas registradas pelo usuario.

**Exemplo de corpo do response**
```js
{
    "registros": [
        {
          "data": "2023-03-02",
          "titulo": "Tarefas 02/03"
          "descricao": "Ir ao mercado, comprar arroz e tomate. Buscar as crianças na escola."
        },
        {
          "data": "2023-03-03",
          "titulo": "Tarefas 03/03"
          "descricao": "Ir a academia. Assistir come and see."
        },
        {
          "data": "2023-03-04",
          "titulo": "Tarefas 04/03"
          "descricao": "Ir a escola. Comprar marlboro. Comprar alface."
        }    
    ]
}
```

**Códigos de Resposta**
| Código | Descrição
|:-:|-
| 200 | Historico recuperado com sucesso
| 400 | Erro na requisição
| 404 | Usuario não encontrado
| 404 | Historico não encontrado

---
