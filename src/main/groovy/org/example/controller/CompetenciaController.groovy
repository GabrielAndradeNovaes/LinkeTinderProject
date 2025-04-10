package org.example.controller

import org.example.model.Competencia
import org.example.Dao.CompetenciaRepository

class CompetenciaController {
    CompetenciaRepository competenciaRepository = new CompetenciaRepository()\

    CompetenciaController(CompetenciaRepository competenciaRepository){
        this.competenciaRepository = competenciaRepository
    }

    void adicionarCompetencia(String nome) {
        Competencia competencia = new Competencia(nome)
        competenciaRepository.inserir(competencia)
        println "✅ Competência cadastrada com sucesso!"
    }

    List<Competencia> listarCompetencias() {
        return competenciaRepository.listarTodas()
    }

    void apagarCompetencia(String nome) {
        competenciaRepository.apagar(nome)
        println "✅ Competência removida com sucesso!"
    }
}
