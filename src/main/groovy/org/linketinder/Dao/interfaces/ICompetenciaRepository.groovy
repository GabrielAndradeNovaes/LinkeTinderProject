package org.linketinder.Dao.interfaces

import org.linketinder.model.Competencia

interface ICompetenciaRepository {

    void inserir(Competencia competencia)

    Competencia listarUm(int id)

    List<Competencia> listarTodas()

    void apagar(int id)
}