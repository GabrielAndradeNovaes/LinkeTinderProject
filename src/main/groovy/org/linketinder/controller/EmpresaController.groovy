package org.linketinder.controller

import org.linketinder.Dao.interfaces.IEmpresaRepository
import org.linketinder.model.Empresa
import org.linketinder.model.Competencia

class EmpresaController {

    private final IEmpresaRepository empresaRepository

    EmpresaController(IEmpresaRepository empresaRepository) {
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
