package org.linketinder.factory

import org.linketinder.view.*

class ViewFactory {

    static CandidatoView criarCandidatoView() {
        return new CandidatoView(ControllerFactory.criarCandidatoController())
    }

    static EmpresaView criarEmpresaView() {
        return new EmpresaView(ControllerFactory.criarEmpresaController())
    }

    static VagasView criarVagasView() {
        return new VagasView(ControllerFactory.criarVagasController())
    }

    static CompetenciaView criarCompetenciaView() {
        return new CompetenciaView(ControllerFactory.criarCompetenciaController())
    }
}
