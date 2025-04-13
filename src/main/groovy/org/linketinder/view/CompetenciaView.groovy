package org.linketinder.view

import org.linketinder.controller.CompetenciaController
import org.linketinder.model.Competencia

class CompetenciaView {
    Scanner scanner = new Scanner(System.in)
    CompetenciaController controller = new CompetenciaController()

    CompetenciaView(CompetenciaController controller) {
        this.controller = controller
    }

    void exibirMenu() {
        while (true) {
            println "\n=== MENU DE COMPETÊNCIAS ==="
            println "1 - Adicionar Competência"
            println "2 - Listar Competências"
            println "3 - Remover Competência"
            println "4 - Voltar"
            print "Escolha uma opção: "

            int opcao = scanner.nextInt()
            scanner.nextLine()

            switch (opcao) {
                case 1:
                    adicionarCompetencia()
                    break
                case 2:
                    listarCompetencias()
                    break
                case 3:
                    removerCompetencia()
                    break
                case 4:
                    return
                default:
                    println "❌ Opção inválida!"
            }
        }
    }

    void adicionarCompetencia() {
        print "Nome da Competência: "
        String nome = scanner.nextLine()
        boolean sucesso = controller.adicionarCompetencia(nome)
        if (sucesso) {
            println "✅ Competência cadastrada com sucesso!"
        } else {
            println "❌ Erro ao cadastrar a competência."
        }
    }

    void listarCompetencias() {
        List<Competencia> competencias = controller.listarCompetencias()
        if (competencias.isEmpty()) {
            println "⚠ Nenhuma competência cadastrada!"
        } else {
            competencias.each { println it }
        }
    }

    void removerCompetencia() {
        print "ID da Competência a remover: "
        int id = scanner.nextInt()
        boolean sucesso = controller.apagarCompetencia(id)
        if (sucesso) {
            println "✅ Competência removida com sucesso!"
        } else {
            println "❌ Erro ao remover a competência."
        }
    }
}
