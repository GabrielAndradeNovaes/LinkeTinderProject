package org.linketinder.factory
import org.linketinder.controller.*
import org.linketinder.Dao.*

class ControllerFactory {
    static CandidatoController criarCandidatoController() {
        new CandidatoController((new CandidatoRepository()))
    }

    static EmpresaController criarEmpresaController() {
        return new EmpresaController(new EmpresaRepository())
    }

    static VagasController criarVagasController() {
        return new VagasController(new VagasRepository())
    }

    static CompetenciaController criarCompetenciaController() {
        return new CompetenciaController(new CompetenciaRepository())
    }
}
