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
import org.linketinder.factory.*

class AppConfig {

    final CandidatoView candidatoView
    final EmpresaView empresaView
    final VagasView vagasView
    final CompetenciaView competenciaView

    AppConfig() {

        this.candidatoView = ViewFactory.criarCandidatoView()
        this.empresaView = ViewFactory.criarEmpresaView()
        this.vagasView = ViewFactory.criarVagasView()
        this.competenciaView = ViewFactory.criarCompetenciaView()

    }
}
