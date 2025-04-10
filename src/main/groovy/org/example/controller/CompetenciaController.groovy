package org.example.controller

import org.example.model.Competencia
import org.example.Dao.CompetenciaRepository

class CompetenciaController {
    CompetenciaRepository competenciaRepository = new CompetenciaRepository()

    CompetenciaController(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository
    }

    boolean adicionarCompetencia(String nome) {
        try {
            Competencia competencia = new Competencia(nome)
            competenciaRepository.inserir(competencia)
            return true
        } catch (Exception e) {
            return false
        }
    }

    List<Competencia> listarCompetencias() {
        return competenciaRepository.listarTodas()
    }

    boolean apagarCompetencia(String nome) {
        try {
            competenciaRepository.apagar(nome)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
