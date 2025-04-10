package org.example.controller

import org.example.model.*
import org.example.view.*
import org.example.Dao.*

class CandidatoController {
    CandidatoRepository candidatoRepository = new CandidatoRepository()

    CandidatoController(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository
    }

    void adicionarCliente(String nome, String email, String cpf , int idade, String cep, String estado, List<Competencia> competencias) {
        Candidato candidato = new Candidato(nome: nome, email: email, cpf: cpf, idade: idade, cep: cep, estado: estado, competencias)
        candidatoRepository.inserir(candidato)
        println "✅ Cliente cadastrado com sucesso!"
    }

    List<Candidato> listarCandidatos() {
        return candidatoRepository.listarTodos()
    }

    Candidato buscarCandidato(int id) {
        return candidatoRepository.listarUm(id)
    }

    void apagarCandidato(int id) {
        candidatoRepository.apagar(id)
        println "✅ Candidato removido com sucesso!"
    }
}
