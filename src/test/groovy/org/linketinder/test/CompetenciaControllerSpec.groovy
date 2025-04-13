package org.linketinder.test

import org.linketinder.controller.CompetenciaController
import org.linketinder.model.Competencia
import org.linketinder.Dao.CompetenciaRepository
import spock.lang.Specification

class CompetenciaControllerSpec extends Specification {

    CompetenciaRepository competenciaRepository
    CompetenciaController competenciaController

    void setup() {
        competenciaRepository = Mock(CompetenciaRepository)
        competenciaController = new CompetenciaController(competenciaRepository)
    }

    void "Deve adicionar uma competência com sucesso"() {
        given: "Nome de uma nova competência"
        String nome = "Java"

        when: "O método adicionarCompetencia é chamado"
        competenciaController.adicionarCompetencia(nome)

        then: "A competência é inserida no repositório com o nome correto"
        1 * competenciaRepository.inserir({
            it.nome == nome
        })
    }

    void "Deve listar todas as competências"() {
        given: "Uma lista de competências"
        Competencia comp1 = new Competencia("Java")
        Competencia comp2 = new Competencia("Groovy")
        competenciaRepository.listarTodas() >> [comp1, comp2]

        when: "O método listarCompetencias é chamado"
        List<Competencia> competencias = competenciaController.listarCompetencias()

        then: "A lista de competências deve ser retornada corretamente"
        competencias.size() == 2
        competencias[0].nome == "Java"
        competencias[1].nome == "Groovy"
    }

    void "Deve apagar uma competência com sucesso"() {
        given: "Nome da competência a ser apagada"
        int id = 1

        when: "O método apagarCompetencia é chamado"
        competenciaController.apagarCompetencia(id)

        then: "O método apagar deve ser chamado uma vez com o nome correto"
        1 * competenciaRepository.apagar(id)
    }
}
