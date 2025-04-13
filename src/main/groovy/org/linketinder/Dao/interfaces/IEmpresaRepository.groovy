package org.linketinder.Dao.interfaces

import org.linketinder.model.Empresa

interface IEmpresaRepository {

    void inserir(Empresa empresa)

    Empresa listarUma(int id)

    List<Empresa> listarTodas()

    void apagar(int id)

}