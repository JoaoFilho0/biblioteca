# API Biblioteca — Documentação dos Endpoints

Documentação dos endpoints do controller de itens/empréstimos da biblioteca, com exemplos de payloads JSON para teste.

## Sumário

- [POST /](#post-)
- [GET /](#get-)
- [GET /mapa](#get-mapa)
- [GET /emprestimo/relatorio](#get-emprestimorelatorio)
- [GET /multa/{itemId}](#get-itemidmulta)
- [POST /emprestimo/{itemId}](#post-emprestimoitemid)
- [PATCH /devolucao/{itemId}](#patch-devolucaoitemid)

---

## POST `/`

Cria um novo item da biblioteca. O campo `tipo` define qual classe concreta será instanciada pela `ItemFactory` (`LIVRO`, `REVISTA` ou `EBOOK`), e os demais campos relevantes variam de acordo com o tipo.

### Exemplo — Livro

```json
{
  "tipo": "LIVRO",
  "titulo": "O Senhor dos Anéis",
  "anoPublicacao": 1954,
  "isbn": "978-0544003415",
  "numeroPaginas": 1178
}
```

### Exemplo — Revista

```json
{
  "tipo": "REVISTA",
  "titulo": "National Geographic Brasil",
  "anoPublicacao": 2026,
  "edicao": 312,
  "periodicidade": "MENSAL"
}
```

### Exemplo — Ebook

```json
{
  "tipo": "EBOOK",
  "titulo": "Clean Code",
  "anoPublicacao": 2008,
  "formato": "EPUB",
  "tamanhoArquivo": 4.7
}
```

**Resposta:** `200 OK` com `ItemResponseDTO`.

---

## GET `/`

Lista todos os itens cadastrados na biblioteca.

Sem corpo de requisição.

**Resposta:** `200 OK` com lista de `ItemResponseDTO`.

---

## GET `/mapa`

Lista todos os itens cadastrados, indexados por ID.

Sem corpo de requisição.

**Resposta:** `200 OK` com `Map<Long, ItemResponseDTO>`.

---

## GET `/emprestimo/relatorio`

Gera um relatório textual de empréstimos.

Sem corpo de requisição.

**Resposta:** `200 OK` com conteúdo `text/plain`.

---

## GET `/multa/{itemId}`

Calcula a multa de um item com base nos dias de atraso.

Sem corpo de requisição. Parâmetros via path e query string:

```
GET /multa/1?diasAtraso=5
```

| Parâmetro   | Tipo | Local      | Descrição                         |
|-------------|------|------------|------------------------------------|
| itemId      | Long | path       | ID do item                         |
| diasAtraso  | int  | query      | Quantidade de dias de atraso       |

**Resposta:** `200 OK` com `MultaResponseDTO`.

---

## POST `/emprestimo/{itemId}`

Registra o empréstimo de um item para um leitor.

```
POST /emprestimo/1
```

### Payload

```json
{
  "leitor": "Maria Silva",
  "diasParaDevolucao": 14
}
```

**Resposta:** `201 Created` com `EmprestimoResponseDTO`.

---

## PATCH `/devolucao/{itemId}`

Registra a devolução de um item emprestado.

```
PATCH /devolucao/1
```

Sem corpo de requisição.

**Resposta:** `200 OK` com `EmprestimoResponseDTO`.

---

## Observações

- Os valores de `tipo` aceitos por `ItemFactory` são `LIVRO`, `REVISTA` e `EBOOK` (case-insensitive, convertidos via `toUpperCase()`).
- Caso `tipo` seja inválido ou não reconhecido, a fábrica lança `IllegalArgumentException`.