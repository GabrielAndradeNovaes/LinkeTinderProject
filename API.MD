# Documentação das APIs e Alterações Realizadas

Este documento descreve as mudanças estruturais feitas no projeto **Linketinder** para incluir suporte a comunicação via **API HTTP**, além de explicar como utilizar os novos **endpoints** REST criados para as entidades: **Candidato**, **Empresa**, **Competência** e **Vaga**.

---

## ✅ Alterações Realizadas

### 1. Criação de Servidor HTTP básico

Foi adicionado um servidor embutido usando a classe `HttpServer` da JDK, **sem uso de frameworks externos** como Spring Boot, para manter o projeto leve e didático.

Agora, o projeto aceita requisições HTTP nas portas:

- `/api/candidato`
- `/api/empresa`
- `/api/competencia`
- `/api/vaga`

### 2. Inclusão de Classes `API` para cada entidade

Foram criadas classes como `CandidatoAPI`, `EmpresaAPI`, `CompetenciaAPI` e `VagaAPI` responsáveis por:

- Interpretar requisições HTTP.
- Delegar as ações para os respectivos controllers.
- Retornar respostas JSON com status HTTP adequado.

### 3. Novos Endpoints Disponíveis

#### 🧑‍💼 Candidato

| Método | Endpoint             | Ação                      |
|--------|----------------------|---------------------------|
| GET    | `/api/candidato`     | Lista todos os candidatos |
| POST   | `/api/candidato`     | Adiciona um novo candidato|

**Exemplo JSON para POST**:
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "cpf": "12345678900",
  "idade": 30,
  "estado": "SP",
  "cep": "12345-678",
  "descricao": "Desenvolvedor Full Stack",
  "competencias": ["Java", "Angular", "SQL"]
}

# 🏢 Empresa

| Método | Endpoint       | Ação                   |
|--------|----------------|------------------------|
| GET    | /api/empresa   | Lista todas as empresas |
| POST   | /api/empresa   | Adiciona nova empresa  |

### Exemplo JSON para POST:

```json
{
  "nome": "Tech Corp",
  "emailCorporativo": "contato@techcorp.com",
  "cnpj": "11222333444455",
  "pais": "Brasil",
  "estado": "RJ",
  "cep": "12345-678",
  "descricao": "Empresa de tecnologia",
  "competenciasDesejadas": ["Java", "Spring Boot"]
}
```

# 🧠 Competência

| Método | Endpoint           | Ação                         |
|--------|--------------------|------------------------------|
| GET    | /api/competencia   | Lista todas as competências  |
| POST   | /api/competencia   | Adiciona nova competência    |

### Exemplo JSON para POST:

```json
{
  "nome": "Groovy"
}
```

# 💼 Vaga

| Método | Endpoint     | Ação                 |
|--------|--------------|----------------------|
| GET    | /api/vaga    | Lista todas as vagas |
| POST   | /api/vaga    | Adiciona nova vaga   |

### Exemplo JSON para POST:

```json
{
  "nome": "Desenvolvedor Backend",
  "descricao": "Vaga para desenvolvedor Java/Groovy",
  "empresa": {
    "nome": "Tech Corp",
    "emailCorporativo": "contato@techcorp.com",
    "cnpj": "11222333444455",
    "pais": "Brasil",
    "estado": "RJ",
    "cep": "12345-678",
    "descricao": "Empresa de tecnologia",
    "competenciasDesejadas": ["Groovy", "SQL"]
  }
}
```

# 📦 Organização do Código

As novas classes `*API.groovy` estão localizadas no pacote:

```
src/
└── org/
    └── linketinder/
        ├── api/
        │   ├── CandidatoAPI.groovy
        │   ├── EmpresaAPI.groovy
        │   ├── CompetenciaAPI.groovy
        │   └── VagaAPI.groovy
```

Essas classes foram conectadas ao servidor HTTP principal na classe `App.groovy`, que agora contém registros como:

```groovy
httpServer.createContext("/api/candidato", new CandidatoAPI())
```

# ▶️ Como Usar os Endpoints

Você pode testar os endpoints com ferramentas como:

- Postman
- `curl` no terminal
- Extensões REST Client em editores como VSCode

### Exemplo com curl:

```bash
curl -X POST http://localhost:8080/api/candidato \
  -H "Content-Type: application/json" \
  -d '{"nome":"João","email":"joao@email.com","cpf":"123","idade":22,"estado":"RJ",
       "cep":"11111-111","descricao":"dev","competencias":["Groovy"]}'
```