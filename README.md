# Aplicativo de Gestão de Clientes (Jetpack Compose + Ktor)

Este projeto consiste em desenvolver um **aplicativo mobile de gestão de clientes** utilizando **Jetpack Compose** para a interface do usuário e o cliente **Ktor** para comunicação com uma API de backend customizada.
O foco está em criar uma **interface moderna e responsiva**, com **boas práticas de UI/UX**, e um fluxo de navegação para autenticação e visualização de dados de clientes.

## 🔑 Credenciais de Autenticação

Para acessar o aplicativo, utilize as seguintes credenciais de teste:

| Campo | Valor |
| :--- | :--- |
| **Email** | `mobile@iftm.edu.br` |
| **Senha** | `mobile123` |

---

## ⚠️ Observação

Este aplicativo utiliza uma API hospedada no serviço gratuito [onrender.com](https://extensaoiii-api.onrender.com). Devido ao plano de hospedagem ser gratuito, o servidor pode hibernar após um período de inatividade.

- **Consequência:** As primeiras requisições (principalmente o login inicial) podem demorar mais que o normal ou, em alguns casos, falhar.
- **Solução:** Caso ocorra um erro de conexão ou *timeout*, por favor, aguarde alguns segundos e tente a operação de login novamente. O servidor deve estar ativo após a primeira tentativa, permitindo o funcionamento normal do aplicativo.

## 📱 Features

- **Autenticação e Acesso a Dados**
  - **Login de Usuários** com e-mail e senha, autenticando em uma API externa.
  - Armazenamento de `token` de autenticação para requisições seguras.
  - Opção de **logout** na tela inicial para encerrar a sessão do usuário.

- **Gestão de Clientes**
  - **Listagem de Clientes** com busca e filtragem por nome em tempo real.
  - **Visualização de Detalhes** de um cliente específico (nome, e-mail, telefone, CPF e total em compras).
  - Comunicação com a API de clientes via Ktor.

- **UI/UX – Jetpack Compose + Material 3**
  - Interface desenvolvida inteiramente com **Jetpack Compose** e o tema **Material 3**.
  - **Design moderno** com gradiente de fundo nas telas de autenticação e detalhes do cliente.
  - Campo de senha com opção de **exibir/ocultar senha**.
  - Uso de **Snackbars** para feedbacks em vez de Toasts.
  - Tela de detalhes do cliente com avatar dinâmico.

<img src="https://github.com/user-attachments/assets/1436da54-aeea-44fa-b717-b0b031ed7a59" alt="Tela de Login" width="300"/>
<img src="https://github.com/user-attachments/assets/cf71550c-b009-4953-b909-2592d5feb32e" alt="Tela Inicial - Lista de Clientes" width="300"/>
<img src="https://github.com/user-attachments/assets/9d7df61e-55d4-4c8b-a6be-ec2d51e75151" alt="Tela de Detalhes do Cliente" width="300"/>

## 🛠️ Technologies Used

- **Language:** Kotlin
- **Framework:** Jetpack Compose (Material 3)
- **Networking:** Ktor Client
- **Data Serialization:** Kotlinx Serialization
- **IDE:** Android Studio
- **Build System:** Gradle

## 🚀 Highlights

- Totalmente desenvolvido com **Jetpack Compose**.
- Design responsivo e consistente com **Material Design 3**.
- Uso do cliente HTTP **Ktor** para comunicação com a API.
- Implementação de funcionalidade de busca/filtro na tela de listagem de clientes.
