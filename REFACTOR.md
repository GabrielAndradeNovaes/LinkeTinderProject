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

