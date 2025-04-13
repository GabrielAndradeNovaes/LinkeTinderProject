package org.linketinder.Dao.interfaces
import org.linketinder.model.Candidato

interface ICandidatoRepository {

    void inserir(Candidato candidato)

    Candidato listarUm(int id)

    List<Candidato> listarTodos()

    void apagar(int id)

}
