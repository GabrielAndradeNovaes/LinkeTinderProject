package org.linketinder.test

import org.linketinder.controller.VagasController
import org.linketinder.Dao.VagasRepository
import org.linketinder.model.Empresa
import org.linketinder.model.Vagas
import spock.lang.Specification

class VagasControllerSpec extends Specification {

    VagasRepository vagasRepository
    VagasController vagasController

    void setup() {
        vagasRepository = Mock(VagasRepository)
        vagasController = new VagasController(vagasRepository)
    }

    void "Deve adicionar uma vaga com sucesso"() {
        given: "Uma empresa e os dados da vaga"
        Empresa empresa = new Empresa(1, "ACME Corp")
        String nome = "Desenvolvedor Back-end"
        String descricao = "Desenvolver APIs REST em Groovy"

        when: "O método adicionarVaga é chamado"
        vagasController.adicionarVaga(nome, descricao, empresa)

        then: "A vaga é inserida no repositório com os dados corretos"
        1 * vagasRepository.inserir({
            it.nome == nome &&
                    it.descricao == descricao &&
                    it.empresa == empresa
        })
    }

    void "Deve listar todas as vagas"() {
        given: "Uma lista de vagas"
        Empresa empresa1 = new Empresa(1, "ACME Corp")
        Empresa empresa2 = new Empresa(2, "Tech Solutions")

        Vagas vaga1 = new Vagas("Dev Java", "Trabalhar com Spring Boot", empresa1)
        Vagas vaga2 = new Vagas("Dev Front-end", "Trabalhar com React", empresa2)

        vagasRepository.listarTodas() >> [vaga1, vaga2]

        when: "O método listarVagas é chamado"
        List<Vagas> vagas = vagasController.listarVagas()

        then: "A lista de vagas deve ser retornada corretamente"
        vagas.size() == 2
        vagas[0].nome == "Dev Java"
        vagas[0].empresa.nome == "ACME Corp"
        vagas[1].nome == "Dev Front-end"
        vagas[1].empresa.nome == "Tech Solutions"
    }

    void "Deve apagar uma vaga com sucesso"() {
        given: "O nome da vaga a ser apagada"
        String nome = "Dev Java"

        when: "O método apagarVaga é chamado"
        vagasController.apagarVaga(nome)

        then: "O método apagar deve ser chamado com o nome correto"
        1 * vagasRepository.apagar(nome)
    }
}
