package org.example.controller

import org.example.model.Vagas
import org.example.model.Empresa
import org.example.Dao.VagasRepository

class VagasController {
    VagasRepository vagasRepository = new VagasRepository()

    VagasController(VagasRepository vagasRepository) {
        this.vagasRepository = vagasRepository
    }

    boolean adicionarVaga(String nome, String descricao, Empresa empresa) {
        try {
            Vagas vaga = new Vagas(nome, descricao, empresa)
            vagasRepository.inserir(vaga)
            return true
        } catch (Exception e) {
            return false
        }
    }

    List<Vagas> listarVagas() {
        return vagasRepository.listarTodas()
    }

    boolean apagarVaga(String nome) {
        try {
            vagasRepository.apagar(nome)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
