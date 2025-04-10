package org.example.controller

import org.example.model.Empresa
import org.example.model.Competencia
import org.example.Dao.EmpresaRepository

class EmpresaController {

    EmpresaRepository empresaRepository = new EmpresaRepository()

    EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository
    }

    boolean adicionarEmpresa(String nome, String email, String cnpj, String pais, String cep, List<Competencia> competencias) {
        try {
            Empresa empresa = new Empresa(nome: nome, email: email, cnpj: cnpj, pais: pais, cep: cep, competencias: competencias)
            empresaRepository.inserir(empresa)
            return true
        } catch (Exception e) {
            return false
        }
    }

    List<Empresa> listarEmpresas() {
        return empresaRepository.listarTodas()
    }

    Empresa buscarEmpresa(int id) {
        return empresaRepository.listarUma(id)
    }

    boolean apagarEmpresa(int id) {
        try {
            empresaRepository.apagar(id)
            return true
        } catch (Exception e) {
            return false
        }
    }
}
