package org.example.test

import org.example.controller.CandidatoController
import org.example.model.Candidato
import org.example.Dao.CandidatoRepository
import spock.lang.Specification

class CandidatoSpec extends Specification {

    CandidatoRepository candidatoRepository
    CandidatoController candidatoController

    def setup() {
        candidatoRepository = Mock(CandidatoRepository)
        candidatoController = new CandidatoController(candidatoRepository)
    }

    def "Deve adicionar um candidato com sucesso"() {
        given: "Informações de um novo candidato"
        String nome = "João Silva"
        String email = "joao@email.com"
        String cpf = "123.456.789-10"
        int idade = 30
        String cep = "01000-000"
        String estado = "SP"

        when: "O método adicionarCliente é chamado"
        candidatoController.adicionarCliente(nome, email, cpf, idade, cep, estado)

        then: "O candidato é inserido no repositório com os dados corretos"
        1 * candidatoRepository.inserir({
            it.nome == nome &&
                    it.email == email &&
                    it.cpf == cpf &&
                    it.idade == idade &&
                    it.cep == cep &&
                    it.estado == estado
        })
    }

    def "Deve listar todos os candidatos"() {
        given: "Uma lista de candidatos"
        def candidato1 = new Candidato(nome: "João Silva", email: "joao@email.com", cpf: "123.456.789-10", idade: 30, estado: "SP", cep: "01000-000")
        def candidato2 = new Candidato(nome: "Maria Souza", email: "maria@email.com", cpf: "987.654.321-00", idade: 25, estado: "RJ", cep: "02000-000")
        candidatoRepository.listarTodos() >> [candidato1, candidato2]

        when: "O método listarCandidatos é chamado"
        def candidatos = candidatoController.listarCandidatos()

        then: "A lista de candidatos deve ser retornada corretamente"
        candidatos.size() == 2
        candidatos[0].nome == "João Silva"
        candidatos[1].nome == "Maria Souza"
    }

    def "Deve buscar um candidato por ID"() {
        given: "ID do candidato a ser buscado"
        int candidatoId = 1
        def candidato = new Candidato(nome: "João Silva", email: "joao@email.com", cpf: "123.456.789-10", idade: 30, estado: "SP", cep: "01000-000")
        candidatoRepository.listarUm(candidatoId) >> candidato

        when: "O método buscarCandidato é chamado"
        def resultado = candidatoController.buscarCandidato(candidatoId)

        then: "O candidato correto deve ser retornado"
        resultado != null
        resultado.nome == "João Silva"
    }

    def "Deve apagar um candidato com sucesso"() {
        given: "ID do candidato a ser apagado"
        int candidatoId = 1

        when: "O método apagarCandidato é chamado"
        candidatoController.apagarCandidato(candidatoId)

        then: "O método apagar deve ser chamado uma vez com o ID correto"
        1 * candidatoRepository.apagar(candidatoId)
    }
}
