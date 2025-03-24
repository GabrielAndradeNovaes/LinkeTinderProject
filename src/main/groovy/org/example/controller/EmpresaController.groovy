package org.example.controller

import org.example.model.Empresa
import org.example.model.Competencia
import org.example.Dao.EmpresaRepository

class EmpresaController {

        EmpresaRepository empresaRepository = new EmpresaRepository()

        void adicionarEmpresa(String nome, String email, String cnpj, String pais, String cep, List<Competencia> competencias) {
            Empresa empresa = new Empresa(nome: nome, email: email, cnpj: cnpj, pais: pais, cep: cep, competencias: competencias)
            empresaRepository.inserir(empresa)
            println "✅ Empresa cadastrada com sucesso!"
        }

        List<Empresa> listarEmpresas() {
            return empresaRepository.listarTodas()
        }

        Empresa buscarEmpresa(int id) {
            return empresaRepository.listarUma(id)
        }

        void apagarEmpresa(int id) {
            empresaRepository.apagar(id)
            println "✅ Empresa removida com sucesso!"
        }
    }
