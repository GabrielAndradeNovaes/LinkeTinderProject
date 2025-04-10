package org.linketinder.test

import org.linketinder.controller.CandidatoController
import org.linketinder.model.Candidato
import org.linketinder.Dao.CandidatoRepository
import spock.lang.Specification

class CandidatoControllerSpec extends Specification {

    CandidatoRepository candidatoRepository
    CandidatoController candidatoController

    void setup() {
        candidatoRepository = Mock(CandidatoRepository)
        candidatoController = new CandidatoController(candidatoRepository)
    }

    void "Adiciona um novo candidato com dados válidos"() {
        given:
        String nome = "João Silva"
        String email = "joao@email.com"
        String cpf = "123.456.789-10"
        int idade = 30
        String cep = "01000-000"
        String estado = "SP"

        Candidato novoCandidato = new Candidato(
                nome: nome,
                email: email,
                cpf: cpf,
                idade: idade,
                cep: cep,
                estado: estado
        )

        when:
        candidatoController.adicionarCliente(novoCandidato)

        then:
        1 * candidatoRepository.inserir({
            it.nome == nome &&
                    it.email == email &&
                    it.cpf == cpf &&
                    it.idade == idade &&
                    it.cep == cep &&
                    it.estado == estado
        })
    }

    void "Retorna todos os candidatos cadastrados"() {
        given:
        Candidato candidato1 = new Candidato(
                nome: "João Silva",
                email: "joao@email.com",
                cpf: "123.456.789-10",
                idade: 30,
                estado: "SP",
                cep: "01000-000"
        )

        Candidato candidato2 = new Candidato(
                nome: "Maria Souza",
                email: "maria@email.com",
                cpf: "987.654.321-00",
                idade: 25,
                estado: "RJ",
                cep: "02000-000"
        )

        candidatoRepository.listarTodos() >> [candidato1, candidato2]

        when:
        List<Candidato> candidatos = candidatoController.listarCandidatos()

        then:
        with(candidatos) {
            size() == 2
            it[0].nome == "João Silva"
            it[1].nome == "Maria Souza"
        }
    }

    void "Retorna um candidato específico ao buscar por ID"() {
        given:
        int candidatoId = 1
        Candidato candidato = new Candidato(
                nome: "João Silva",
                email: "joao@email.com",
                cpf: "123.456.789-10",
                idade: 30,
                estado: "SP",
                cep: "01000-000"
        )

        candidatoRepository.listarUm(candidatoId) >> candidato

        when:
        Candidato resultado = candidatoController.buscarCandidato(candidatoId)

        then:
        resultado.nome == "João Silva"
    }

    void "Remove um candidato ao fornecer um ID válido"() {
        given:
        int candidatoId = 1

        when:
        candidatoController.apagarCandidato(candidatoId)

        then:
        1 * candidatoRepository.apagar(candidatoId)
    }
}
