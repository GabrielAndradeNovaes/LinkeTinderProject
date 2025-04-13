# 📘 Documento de Refatoração

## 🔁 Refatoração das Views

### `refactor(view): encapsula dados em objetos antes de enviar ao controller`

As seguintes views foram refatoradas:
- `CandidatoView`
- `EmpresaView`
- `CompetenciaView`
- `VagasView`

### ✨ O que mudou:
- Agora, ao invés de passar cada atributo separadamente aos métodos do controller, a view:
    - Instancia um objeto correspondente (`Candidato`, `Empresa`, etc.) com os dados informados.
    - Passa esse objeto diretamente ao controller.

### ✅ Benefícios:
- Melhora na legibilidade.
- Facilita a manutenção.
- Aumenta a separação de responsabilidades entre camadas.

---

## 🏗️ AppConfig: Centralização da Configuração

### `feat(config): extrai inicialização de dependências para AppConfig`

Foi criada a classe `AppConfig`, localizada no pacote `org.linketinder.config`, para gerenciar a criação de dependências (`Repositories`, `Controllers` e `Views`).

### 🔨 Mudanças principais:
- **Criação da classe `AppConfig`:**
    - Responsável por instanciar todos os `Repositories`, `Controllers` e `Views`.
    - Reduz acoplamento e repetição no `Main`.
    - As views (`CandidatoView`, `EmpresaView`, `VagasView`, `CompetenciaView`) agora são atributos `final` da `AppConfig`.

- **Refatoração do `Main.groovy`:**
    - Toda a lógica de criação foi substituída por uma única instância de `AppConfig`.
    - Os menus são acessados via `appConfig.<view>.exibirMenu()`.

### 🩽 Vantagens:
- Centraliza a configuração da aplicação.
- Facilita testes, manutenção e futuras modificações.
- Deixa o `main` mais limpo e legível.
- Melhora a separação de responsabilidades.

---

## 📦 Separação de Responsabilidades com Tratamento de Erros

### `refactor: separa responsabilidades entre controller e view com tratamento de erros`

```bash
git commit -m "refactor: separa responsabilidades entre controller e view com tratamento de erros"
```

### 📝 Mudanças:
- Controllers agora retornam `boolean` para ações como adicionar/remover.
- Views são responsáveis por exibir mensagens de sucesso ou erro.
- Adicionado `try/catch` nos métodos de controle para evitar falhas inesperadas.
- Aplicado em: `Competência`, `Empresa`, `Vagas`.

### ✅ Resumo:

#### 1. Separação de responsabilidades
- **Antes:** Controllers e Views exibiam mensagens no terminal.
- **Depois:** Controllers só retornam dados; Views lidam com interface e feedbacks.

#### 2. Tratamento de erros
- **Antes:** Falhas causavam exceções não tratadas.
- **Depois:** Controllers usam `try/catch` e retornam `false` em caso de erro.

#### 3. Legibilidade e coesão
- Ações retornam `boolean`.
- Views decidem mensagens: ✅ sucesso ou ❌ erro.

#### 4. Coerência entre as camadas
Assinatura padronizada nos controllers:
```groovy
boolean adicionarObjeto(...)
boolean removerObjeto(...)
List<Objeto> listarObjetos()
Objeto buscarObjeto(...)
```

---

## 🧪 Refatoração dos Testes

### `refactor(test): melhorias nas specs de Candidato, Competencia, Empresa e Vagas`

### 🔧 Alterações:
- Substituição de `def` por **tipos explícitos** em métodos e variáveis.
- Estrutura `given/when/then` padronizada.
- Asserções refinadas para verificar atributos dos objetos.
- Uso do operador `1 * repositorio.metodo { it.atributo == valor }` para validações.

### ✨ Benefícios:
- **Legibilidade** e clareza nos testes.
- **Robustez** nas validações.
- **Padronização** para facilitar manutenção e colaboração.

### 🧠 Por que foi feito:
- Melhorar a legibilidade e previsibilidade dos testes.
- Facilitar debugging e compreensão.
- Seguir boas práticas com tipagem em testes Groovy/Spock.

### Exemplos de substituição:
```groovy
def setup() → void setup()
def "..."() → void "..."()
def vagas = controller.listarVagas() → List<Vagas> vagas = controller.listarVagas()
```

Objetos como `vaga1`, `vaga2`, `empresa1` e `empresa2` agora possuem tipos explícitos (ex: `Vagas`, `Empresa`, etc).

---

## 🧭 Padronização de Pacotes

### `refactor(package): altera nome de pacote de org.example para org.linketinder`

### 🔄 O que foi feito:
- Todos os pacotes foram renomeados de `org.example` para `org.linketinder`, para refletir corretamente o nome do projeto.

### ✅ Vantagens:
- Nome de pacote mais descritivo e alinhado ao propósito do sistema.
- Facilita organização do código e colaboração.
- Evita conflitos com pacotes genéricos como `example`.

## Refatoração dos Repositórios e Controllers

### `refactor: aplicar interfaces e injeção de dependência em todos os repositórios e controllers`

## 📌 O que foi feito:

- **Criação de Interfaces Específicas para Repositórios**:
  Interfaces como `ICandidatoRepository`, `IEmpresaRepository`, `ICompetenciaRepository`, `IVagaRepository`, entre outras, foram criadas para definir contratos de uso para cada entidade.

- **Implementação das Interfaces nos Repositórios**:
  As classes concretas de repositórios passaram a implementar suas respectivas interfaces, garantindo que todas as operações sejam padronizadas e abstraídas pela interface.

- **Atualização dos Controllers para Injeção de Dependência**:
  Os controllers foram adaptados para receber as dependências via construtor (injeção de dependência), ao invés de criarem suas próprias instâncias dos repositórios. Isso promove maior flexibilidade e facilita a testabilidade.

- **Isolamento entre Camadas de Controle e Persistência**:
  A camada de controle (controllers) e a camada de persistência (repositórios) agora estão desacopladas, melhorando a organização e a manutenção do código.

## 🚀 Benefícios da Refatoração:

- **Aplicação do Princípio da Inversão de Dependência (SOLID)**:
  A refatoração segue o princípio SOLID, especificamente o princípio da inversão de dependência, onde os módulos de alto nível (controllers) não dependem de módulos de baixo nível (repositórios), mas de abstrações (interfaces).

- **Maior Flexibilidade e Extensibilidade**:
  Com as interfaces, é possível criar diferentes implementações de repositórios, como por exemplo, versões em memória, baseadas em banco de dados, arquivos, entre outras. Isso facilita a adaptação do sistema a diferentes necessidades no futuro.

- **Código Mais Testável**:
  A refatoração permite que as interfaces sejam mockadas facilmente nos testes unitários, o que facilita a realização de testes isolados e a garantia de um comportamento correto da aplicação.

- **Arquitetura Mais Limpa, Modular e Sustentável**:
  A aplicação fica mais organizada e a estrutura do código é mais limpa e modular, o que facilita a manutenção e evolução do sistema.

- **Desacoplamento Entre Lógica de Negócio e Camada de Persistência**:
  A lógica de negócio agora está desacoplada da lógica de persistência, permitindo que mudanças na forma como os dados são armazenados ou acessados não impactem diretamente a lógica de negócio, tornando o sistema mais flexível e fácil de modificar.

## 🛠️ Exemplos de Código:

### 1. Interface do Repositório:

```groovy
package org.linketinder.Dao.interfaces

import org.linketinder.model.Candidato

interface ICandidatoRepository {
    void inserir(Candidato candidato)
    Candidato listarUm(int id)
    List<Candidato> listarTodos()
    void apagar(int id)
}