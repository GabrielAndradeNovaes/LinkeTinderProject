package org.example.test

import org.example.controller.CompetenciaController
import org.example.model.Competencia
import org.example.Dao.CompetenciaRepository
import spock.lang.Specification

class CompetenciaSpec extends Specification {

    CompetenciaRepository competenciaRepository
    CompetenciaController competenciaController

    def setup() {
        competenciaRepository = Mock(CompetenciaRepository)
        competenciaController = new CompetenciaController(competenciaRepository)
    }

    def "Deve adicionar uma competência com sucesso"() {
        given: "Nome de uma nova competência"
        String nome = "Java"

        when: "O método adicionarCompetencia é chamado"
        competenciaController.adicionarCompetencia(nome)

        then: "A competência é inserida no repositório com o nome correto"
        1 * competenciaRepository.inserir({
            it.nome == nome
        })
    }

    def "Deve listar todas as competências"() {
        given: "Uma lista de competências"
        def comp1 = new Competencia("Java")
        def comp2 = new Competencia("Groovy")
        competenciaRepository.listarTodas() >> [comp1, comp2]

        when: "O método listarCompetencias é chamado"
        def competencias = competenciaController.listarCompetencias()

        then: "A lista de competências deve ser retornada corretamente"
        competencias.size() == 2
        competencias[0].nome == "Java"
        competencias[1].nome == "Groovy"
    }

    def "Deve apagar uma competência com sucesso"() {
        given: "Nome da competência a ser apagada"
        String nome = "Java"

        when: "O método apagarCompetencia é chamado"
        competenciaController.apagarCompetencia(nome)

        then: "O método apagar deve ser chamado uma vez com o nome correto"
        1 * competenciaRepository.apagar(nome)
    }
}
