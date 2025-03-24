# Gabriel Andrade Novaes

# Linketinder - Sistema de Contratação de Funcionários

Este projeto é um sistema simples, inspirado no LinkedIn e Tinder, desenvolvido em Groovy. Ele foi criado para atender às necessidades de recrutamento de funcionários, permitindo que candidatos e empresas interajam de forma simples, através de um "match" baseado nas competências de ambos.

## Descrição

O sistema foi projetado para gerenciar candidatos e empresas, com funcionalidades como listagem de dados e cadastro de novos candidatos e empresas. A ideia é proporcionar uma forma prática para as empresas encontrarem candidatos com as competências que elas procuram, assim como candidatos que se interessem pelas vagas das empresas.

### Funcionalidades principais:
- **Listar candidatos**: Exibe a lista de candidatos cadastrados no sistema.
- **Listar empresas**: Exibe a lista de empresas cadastradas no sistema.
- **Adicionar candidato**: Permite adicionar um novo candidato ao sistema, preenchendo suas informações pessoais e competências.
- **Adicionar empresa**: Permite adicionar uma nova empresa ao sistema, preenchendo suas informações e competências esperadas dos candidatos.
- **Fazer match**: (Futuramente será implementado) Funcionalidade para realizar o "match" entre empresas e candidatos com base nas competências.

## Estrutura de Dados

- **Candidato**: Cada candidato possui as seguintes informações:
  - Nome
  - E-mail
  - CPF
  - Idade
  - Estado
  - CEP
  - Descrição
  - Competências (array de competências como Python, Java, Spring, Angular, etc.)

- **Empresa**: Cada empresa possui as seguintes informações:
  - Nome
  - E-mail Corporativo
  - CNPJ
  - País
  - Estado
  - CEP
  - Descrição
  - Competências esperadas dos candidatos

## Tecnologias utilizadas
- **Groovy**: Linguagem de programação utilizada para o desenvolvimento do sistema.
- **Scanner**: Utilizado para capturar entradas do usuário no terminal.
- **Spock Framework**: Utilizado para criação de testes automatizados.

## Como Executar o Projeto

### Pré-requisitos
- Você precisa ter o **Groovy** instalado em sua máquina. Caso ainda não tenha, siga os passos de instalação no [site oficial do Groovy](https://groovy-lang.org/download.html).

### Passos para rodar o projeto
1. Faça o clone deste repositório em sua máquina local.
   ```bash
   git clone https://github.com/seu-usuario/linketinder.git
2. Navegue até o diretório do projeto:
   ```bash
   cd linketinder
3. Compile e execute o projeto com o Groovy:
   ```bash
   groovy org/example/Main.groovy

![Imagem da modelagem de dados](./frontend/modelagem.png)
