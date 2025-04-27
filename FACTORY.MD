# 📑 Documentação de Refatoração — Linketinder

## Objetivo

Refatorar o projeto **Linketinder** aplicando **Padrões de Projeto** para melhorar:
- Organização
- Manutenção
- Evolução futura
- Legibilidade para novos desenvolvedores.

---

## 🔥 Padrões de Projeto Aplicados

### 1. Factory Pattern

Criação de duas fábricas:
- `ControllerFactory`: Responsável por instanciar Controllers.
- `ViewFactory`: Responsável por instanciar Views, já configuradas com seus respectivos Controllers.
- `ConnectionFactory`: ConnectionFactory: Responsável por fornecer conexões com o Banco de Dados, permitindo trocar o banco facilmente.

**Antes da Refatoração**:
- A criação dos objetos (Repositories, Controllers e Views) era feita manualmente dentro da classe `AppConfig`.
- O `AppConfig` estava poluído e difícil de dar manutenção.
- A conexão com o Banco de Dados era rígida e acoplada diretamente ao PostgreSQL.

**Depois da Refatoração**:
- Toda a criação de objetos foi isolada nas fábricas, deixando o `AppConfig` limpo e organizado.
- A conexão com o Banco foi isolada, permitindo no futuro mudar facilmente o tipo de Banco de Dados.

**Exemplo de uso após refatoração**:
```groovy
this.candidatoView = ViewFactory.criarCandidatoView()
```
### 2. Singleton Pattern

- Garantir que exista apenas uma instância da conexão.
- Melhorar o controle de recursos do sistema.
- Evitar problemas de concorrência e múltiplas conexões abertas.

**Como foi implementado:**
- Criamos uma classe `ConnectionFactory` que gerencia a instância única da conexão.
- A instância da conexão é criada apenas quando necessária (lazy initialization).
- A instância é reutilizada durante o ciclo de vida da aplicação, evitando a criação de múltiplas conexões.

**Exemplo de código da implementação**:

```groovy
    class ConnectionFactory {

    private static Connection connection

    static String url = "jdbc:postgresql://localhost:5432/Teste"
    static String user = "geek"
    static String password = "1234"

    private ConnectionFactory() {}

    static Connection getConnection() {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver")
                connection = DriverManager.getConnection(url, user, password)
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace()
            }
        }
        return connection
    }
}
```

## ✨ Melhorias Obtidas

**🔹 Baixo Acoplamento**

- O `AppConfig` não precisa mais saber como criar objetos — só consome.
- Mudanças em DAOs ou Controllers não afetam quem os utiliza.

**🔹 Alta Coesão**

- Cada classe tem agora uma responsabilidade única:
    - Factories criam objetos.
    - Controllers controlam a lógica de negócios.
    - Views se comunicam com o usuário.

**🔹 Facilidade para Testes**

- Possível usar injeção de dependências facilmente.
- Permite substituir implementações por fakes ou mocks nos testes unitários.

**🔹 Código Mais Limpo**

- `AppConfig` agora ficou com poucas linhas.
- O fluxo de instanciar o sistema ficou mais direto e compreensível.

**🔹 Flexibilidade e Escalabilidade**

- Se precisar adicionar novos Controllers ou Views, basta alterar as fábricas.
- Crescimento do sistema será mais seguro e organizado.

## 📜 Resumo das Alterações

| Item                           | Antes da Refatoração           | Depois da Refatoração                                                |
|--------------------------------| ------------------------------ |----------------------------------------------------------------------|
| Instanciação de objetos        | Manual, dentro do `AppConfig`  | Centralizada em `ControllerFactory` e `ViewFactory`                  |
| Criação de Controllers e Views | Direto no `AppConfig`          | Isolada nas Factories                                                |
| Padrões de Projeto             | Nenhum                         | Factory Pattern aplicado e Singleton planejado                       |
| Organização do código          | `AppConfig` extenso e confuso  | `AppConfig` enxuto, organizado e legível                             |
| Facilidade de testes           | Difícil                        | Alta, com suporte a mocks                                            |
| Flexibilidade para alterações  | Baixa                          | Alta, apenas Factories precisam ser alteradas                        |
| Instanciação da Conexão	       | Criada em múltiplos locais | Instância única criada e gerenciada pelo Singleton                   |
| Gestão de Conexões             | Múltiplas conexões abertas | Conexão única, reutilizada durante todo o ciclo de vida da aplicação |
| Possibilidade de trocar Banco  | Rígido (PostgreSQL fixo) | Flexível, alterando apenas parâmetros da ConnectionFactory           |


## ✅ Conclusão

A aplicação dos padrões Factory e Singleton no projeto Linketinder trouxe:

- Melhor organização
- Melhor manutenção
- Facilidade para testes
- Escalabilidade
