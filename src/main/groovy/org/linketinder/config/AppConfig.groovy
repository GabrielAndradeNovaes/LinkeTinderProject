package org.linketinder.config

import org.linketinder.Dao.CandidatoRepository
import org.linketinder.Dao.CompetenciaRepository
import org.linketinder.Dao.EmpresaRepository
import org.linketinder.Dao.VagasRepository
import org.linketinder.controller.CandidatoController
import org.linketinder.controller.CompetenciaController
import org.linketinder.controller.EmpresaController
import org.linketinder.controller.VagasController
import org.linketinder.view.CandidatoView
import org.linketinder.view.CompetenciaView
import org.linketinder.view.EmpresaView
import org.linketinder.view.VagasView

class AppConfig {

    final CandidatoView candidatoView
    final EmpresaView empresaView
    final VagasView vagasView
    final CompetenciaView competenciaView

    AppConfig() {
        CandidatoRepository candidatoRepository = new CandidatoRepository()
        EmpresaRepository empresaRepository = new EmpresaRepository()
        VagasRepository vagasRepository = new VagasRepository()
        CompetenciaRepository competenciaRepository = new CompetenciaRepository()

        CandidatoController candidatoController = new CandidatoController(candidatoRepository)
        EmpresaController empresaController = new EmpresaController(empresaRepository)
        VagasController vagasController = new VagasController(vagasRepository)
        CompetenciaController competenciaController = new CompetenciaController(competenciaRepository)

        this.candidatoView = new CandidatoView(candidatoController)
        this.empresaView = new EmpresaView(empresaController)
        this.vagasView = new VagasView(vagasController)
        this.competenciaView = new CompetenciaView(competenciaController)
    }
}
