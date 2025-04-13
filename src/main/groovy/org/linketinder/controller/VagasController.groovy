package org.linketinder.controller

import org.linketinder.Dao.interfaces.IVagasRepository
import org.linketinder.model.Vagas
import org.linketinder.model.Empresa

class VagasController {
    private final IVagasRepository vagasRepository

    VagasController(IVagasRepository vagasRepository) {
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

    boolean apagarVaga(int id) {
        try {
            vagasRepository.apagar(id)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
