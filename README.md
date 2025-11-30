# API de Pagamentos -- Desafio Técnico

Esta API permite o recebimento, processamento, consulta e exclusão
lógica de pagamentos de débitos de pessoas físicas e jurídicas.

------------------------------------------------------------------------

## Tecnologias Utilizadas

-   **Java 17**
-   **Spring Boot**
-   **Spring Web**
-   **Spring Data JPA**
-   **H2 Database**
-   **Maven**

------------------------------------------------------------------------

## Funcionalidades da API

### 1. **Receber um pagamento**

A API recebe um pagamento contendo: - Código do débito
- CPF/CNPJ
- Método de pagamento (`boleto`, `pix`, `cartao_credito`,
`cartao_debito`)
- Número do cartão (obrigatório apenas para cartões)
- Valor

Ao salvar, o pagamento inicia com status **PENDENTE_PROCESSAMENTO**.

------------------------------------------------------------------------

### 2. **Atualizar status do pagamento**

A API permite alterar o status seguindo as regras:

## Status Atual

| Status Atual | Pode Alterar Para        |
|-------------|---------------------------|
| Pendente    | Sucesso / Falha           |
| Sucesso     | Não pode alterar          |
| Falha       | Pode voltar para Pendente |


------------------------------------------------------------------------

### 3. **Listar e filtrar pagamentos**

Filtros disponíveis: - Código do débito - CPF/CNPJ - Status

------------------------------------------------------------------------

### 4. **Exclusão lógica**

A exclusão só é permitida se o pagamento estiver **Pendente**, e altera
o campo `ativo` para `false`, sem apagar o registro do banco.

------------------------------------------------------------------------

## Endpoints

### ➤ Criar pagamento

`POST /api/pagamentos`

### ➤ Atualizar status

`PUT /api/pagamentos/{id}/status?status=PROCESSADO_SUCESSO`

### ➤ Buscar por ID

`GET /api/pagamentos/{id}`

### ➤ Listar todos

`GET /api/pagamentos`

### ➤ Filtrar

`GET /api/pagamentos?cpfCnpj=123&status=PENDENTE`

### ➤ Exclusão lógica

`DELETE /api/pagamentos/{id}`

------------------------------------------------------------------------

# Exemplos de JSON — Para Testes

## Criar pagamento (boleto)
```json
{
  "codigoDebito": 1001,
  "cpfCnpj": "12345678901",
  "metodoPagamento": "boleto",
  "valor": 150.90
}
```

## Criar pagamento (cartao_credito)
```json
{
  "codigoDebito": 2003,
  "cpfCnpj": "98765432000199",
  "metodoPagamento": "cartao_credito",
  "numeroCartao": "4111111111111111",
  "valor": 350.00
}
```

## Criar pagamento (cartao_debito)
```json
{
  "codigoDebito": 3005,
  "cpfCnpj": "12345678901",
  "metodoPagamento": "cartao_debito",
  "numeroCartao": "5555222233334444",
  "valor": 120.00
}
```

## Criar pagamento (pix)
```json
{
  "codigoDebito": 4007,
  "cpfCnpj": "98765432100",
  "metodoPagamento": "pix",
  "valor": 89.50
}
```

## Atualizar status
PUT /api/pagamentos/1/status?status=PROCESSADO_SUCESSO

PUT /api/pagamentos/2/status?status=PROCESSADO_FALHA

PUT /api/pagamentos/3/status?status=PENDENTE

### Listar todos os pagamentos
`GET /api/pagamentos`

### Buscar pagamento por ID
`GET /api/pagamentos/{id}`

### Filtros
`GET /api/pagamentos?codigoDebito=1001`

`GET /api/pagamentos?cpfCnpj=12345678901`

`GET /api/pagamentos?status=PENDENTE`

`GET /api/pagamentos?codigoDebito=1001&cpfCnpj=12345678901&status=PENDENTE`

## Exclusão lógica
DELETE /api/pagamentos/1

------------------------------------------------------------------------

## Acesso ao H2 Console

URL: `/h2-console`
Driver: `org.h2.Driver`
User: `sa`
Password: *(vazio)*

------------------------------------------------------------------------

## Observações

-   O projeto foi desenvolvido seguindo boas práticas e organização em
    camadas (Controller, Service, Repository).
-   Todas as respostas da API são em **JSON**.
-   Banco H2 facilita o teste e não requer instalação.

