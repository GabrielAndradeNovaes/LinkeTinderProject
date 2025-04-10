package org.example.config

import org.example.Dao.CandidatoRepository
import org.example.Dao.CompetenciaRepository
import org.example.Dao.EmpresaRepository
import org.example.Dao.VagasRepository
import org.example.controller.CandidatoController
import org.example.controller.CompetenciaController
import org.example.controller.EmpresaController
import org.example.controller.VagasController
import org.example.view.CandidatoView
import org.example.view.CompetenciaView
import org.example.view.EmpresaView
import org.example.view.VagasView

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
