package org.example.controller

import org.example.model.Vagas
import org.example.model.Empresa
import org.example.Dao.VagasRepository

class VagasController {
    VagasRepository vagasRepository = new VagasRepository()

    VagasController(VagasRepository vagasRepository){
        this.vagasRepository = vagasRepository
    }

    void adicionarVaga(String nome, String descricao, Empresa empresa) {
        Vagas vaga = new Vagas(nome, descricao, empresa)
        vagasRepository.inserir(vaga)
        println "✅ Vaga cadastrada com sucesso!"
    }

    List<Vagas> listarVagas() {
        return vagasRepository.listarTodas()
    }

    void apagarVaga(String nome) {
        vagasRepository.apagar(nome)
        println "✅ Vaga removida com sucesso!"
    }
}
