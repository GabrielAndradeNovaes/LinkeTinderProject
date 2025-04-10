package org.linketinder.controller

import org.linketinder.model.*
import org.linketinder.Dao.*

class CandidatoController {
    CandidatoRepository candidatoRepository

    CandidatoController(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository
    }

    boolean adicionarCliente(Candidato candidato) {
        try {
            candidatoRepository.inserir(candidato)
            return true
        } catch (Exception e) {
            return false
        }
    }

    List<Candidato> listarCandidatos() {
        return candidatoRepository.listarTodos()
    }

    Candidato buscarCandidato(int id) {
        return candidatoRepository.listarUm(id)
    }

    boolean apagarCandidato(int id) {
        try {
            candidatoRepository.apagar(id)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
