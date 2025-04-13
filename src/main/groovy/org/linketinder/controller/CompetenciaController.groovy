package org.linketinder.controller

import org.linketinder.Dao.interfaces.ICompetenciaRepository
import org.linketinder.model.Competencia
import org.linketinder.Dao.CompetenciaRepository

class CompetenciaController {
    private final ICompetenciaRepository competenciaRepository

    CompetenciaController(ICompetenciaRepository competenciaRepository) {
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

    boolean apagarCompetencia(int id) {
        try {
            competenciaRepository.apagar(id)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
