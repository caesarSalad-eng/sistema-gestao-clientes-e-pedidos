# 📦 Sistema de Gestão de Clientes e Pedidos

Projeto desenvolvido em **Java com Spring Boot**, com foco em **boas práticas de backend**, regras de negócio e organização em camadas.  
O sistema simula um ambiente real de gestão de clientes e pedidos, utilizando menu interativo via terminal.

---

## 🧠 Objetivo do Projeto

Este projeto tem como objetivo praticar e demonstrar conhecimentos em:

- Programação Orientada a Objetos (POO)
- Spring Boot
- Spring Data JPA
- Hibernate
- Relacionamentos entre entidades
- Regras de negócio
- Enumeração de status
- Validações
- Estruturação de um sistema backend realista

---

## 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven
- Banco de dados H2
- Scanner (menu interativo no terminal)

---

## 📐 Arquitetura do Projeto

O projeto segue uma arquitetura em camadas:

model
repository
service
runner (Menu)

### Responsabilidades:
- **Model**: Entidades do sistema
- **Repository**: Acesso ao banco de dados
- **Service**: Regras de negócio e validações
- **Runner/Menu**: Interação com o usuário via terminal

---

## 🧩 Funcionalidades

### 👤 Cliente
- Cadastrar cliente
- Listar clientes
- Atualizar cliente
- Deletar cliente (somente se não possuir pedidos)

### 📦 Pedido
- Criar pedido vinculado a um cliente
- Alterar status do pedido
- Listar pedidos
- Validação de regras de status

---

## 🔄 Status do Pedido

Os pedidos utilizam um `enum` para controle de status:

- `CRIADO`
- `EM_ANDAMENTO`
- `ATRASADO`
- `FINALIZADO`
- `CANCELADO`

### 🔐 Regras de Negócio
- Pedidos **FINALIZADOS** ou **CANCELADOS** não podem ter o status alterado
- Não é permitido criar pedidos para clientes com pedidos atrasados
- Todas as transições de status passam por validação no Service

---

## ⚠️ Validações Implementadas

- ID nulo ou inexistente
- Cliente não encontrado
- Pedido não encontrado
- Cliente com pedidos não pode ser deletado
- Valor do pedido deve ser maior que zero
- Tratamento de erros de entrada no menu (InputMismatchException)
- Prevenção de LazyInitializationException

---

