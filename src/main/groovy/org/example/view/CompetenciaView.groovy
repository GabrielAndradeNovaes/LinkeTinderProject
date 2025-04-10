package org.example.view

import org.example.controller.CompetenciaController
import java.util.Scanner
import org.example.model.Competencia

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
        controller.adicionarCompetencia(nome)
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
        print "Nome da Competência a remover: "
        String nome = scanner.nextLine()
        controller.apagarCompetencia(nome)
    }
}
