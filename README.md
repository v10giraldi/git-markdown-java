# Sistema de Conta Bancária (AccountBank)

Este é um projeto desenvolvido em **Java** que simula as operações básicas de uma conta bancária via terminal (CLI). O sistema gerencia saldos, depósitos, saques e inclui uma lógica automatizada para o uso e cobrança de cheque especial (overdraft).

## 🚀 Funcionalidades

O sistema possui um menu interativo que permite ao usuário realizar as seguintes operações:

- **1. Consultar saldo:** Exibe o saldo atual da conta.
- **2. Consultar cheque especial:** Mostra o limite total e o limite ainda disponível para uso.
- **3. Depositar dinheiro:** Adiciona fundos à conta. Caso haja dívidas de taxas ou uso do cheque especial, o depósito abate esses valores automaticamente antes de compor o saldo.
- **4. Sacar dinheiro:** Realiza a retirada de fundos. Se o saldo for insuficiente, o sistema utiliza automaticamente o limite do cheque especial (sujeito a taxas).
- **5. Pagar boleto:** Funciona de maneira idêntica ao saque, debitando o valor da conta.
- **6. Verificar uso do cheque especial:** Informa se a conta está operando no azul ou se está utilizando o limite extra.
- **0. Sair:** Encerra a aplicação.

## ⚙️ Regras de Negócio

A classe `AccountBank` implementa algumas regras financeiras específicas durante a sua execução:

* **Definição do Limite (Cheque Especial):** * Se o depósito inicial for **menor ou igual a R$ 500,00**, o limite fixo do cheque especial será de **R$ 50,00**.
  * Se o depósito inicial for **maior que R$ 500,00**, o limite será equivalente a **50% do valor depositado**.
* **Taxa de Uso:** * Ao utilizar o cheque especial para cobrir um saque ou pagamento, é gerada uma **taxa de 20%** sobre o valor extra utilizado. Essa taxa é cobrada no próximo depósito realizado.

## 🛠️ Tecnologias Utilizadas

- **Java** (JDK 8 ou superior)
- `java.util.Scanner` para entrada de dados via console.

## 💻 Como Executar

1. Certifique-se de ter o [Java JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/) instalado na sua máquina.
2. Clone este repositório ou faça o download do arquivo `AccountBank.java`.
3. Abra o terminal e navegue até o diretório onde o arquivo está salvo.
4. Compile o código com o comando:
   ```bash
   javac AccountBank.java
