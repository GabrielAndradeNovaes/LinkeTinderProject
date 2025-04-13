package org.linketinder.Dao.interfaces

import org.linketinder.model.Vagas

interface IVagasRepository {

    void inserir(Vagas vagas)

    Vagas listarUm(int id)

    List<Vagas> listarTodas()

    void apagar(int id)

}