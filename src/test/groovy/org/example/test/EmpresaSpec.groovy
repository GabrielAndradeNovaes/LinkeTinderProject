package org.example.test

import org.example.controller.EmpresaController
import org.example.model.Empresa
import org.example.model.Competencia
import org.example.Dao.EmpresaRepository
import spock.lang.Specification

class EmpresaSpec extends Specification {

    EmpresaRepository empresaRepository
    EmpresaController empresaController

    def setup() {
        empresaRepository = Mock(EmpresaRepository)
        empresaController = new EmpresaController(empresaRepository)
    }

    def "Deve adicionar uma empresa com sucesso"() {
        given: "Dados de uma nova empresa"
        String nome = "Tech Corp"
        String email = "contato@techcorp.com"
        String cnpj = "12.345.678/0001-90"
        String pais = "Brasil"
        String cep = "12345-678"
        List<Competencia> competencias = [new Competencia(nome: "Java"), new Competencia(nome: "Spring")]

        when: "O método adicionarEmpresa é chamado"
        empresaController.adicionarEmpresa(nome, email, cnpj, pais, cep, competencias)

        then: "A empresa é inserida no repositório com os dados corretos"
        1 * empresaRepository.inserir({
            it.nome == nome &&
                    it.email == email &&
                    it.cnpj == cnpj &&
                    it.pais == pais &&
                    it.cep == cep &&
                    it.competencias == competencias
        })
    }

    def "Deve listar todas as empresas"() {
        given: "Uma lista de empresas mockada"
        def empresa1 = new Empresa(nome: "Tech Corp", email: "contato@techcorp.com", cnpj: "12.345.678/0001-90", pais: "Brasil", cep: "12345-678", competencias: [])
        def empresa2 = new Empresa(nome: "Inova Ltda", email: "inova@email.com", cnpj: "98.765.432/0001-00", pais: "Brasil", cep: "98765-432", competencias: [])
        empresaRepository.listarTodas() >> [empresa1, empresa2]

        when: "O método listarEmpresas é chamado"
        def resultado = empresaController.listarEmpresas()

        then: "As empresas devem ser retornadas corretamente"
        resultado.size() == 2
        resultado[0].nome == "Tech Corp"
        resultado[1].nome == "Inova Ltda"
    }

    def "Deve buscar uma empresa por ID"() {
        given: "ID da empresa a ser buscada"
        int empresaId = 1
        def empresa = new Empresa(nome: "Tech Corp", email: "contato@techcorp.com", cnpj: "12.345.678/0001-90", pais: "Brasil", cep: "12345-678", competencias: [])
        empresaRepository.listarUma(empresaId) >> empresa

        when: "O método buscarEmpresa é chamado"
        def resultado = empresaController.buscarEmpresa(empresaId)

        then: "A empresa correta deve ser retornada"
        resultado != null
        resultado.nome == "Tech Corp"
    }

    def "Deve apagar uma empresa com sucesso"() {
        given: "ID da empresa a ser apagada"
        int empresaId = 1

        when: "O método apagarEmpresa é chamado"
        empresaController.apagarEmpresa(empresaId)

        then: "O método apagar deve ser chamado uma vez com o ID correto"
        1 * empresaRepository.apagar(empresaId)
    }
}
