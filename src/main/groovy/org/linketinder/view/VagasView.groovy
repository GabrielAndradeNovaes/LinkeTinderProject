package org.linketinder.view

import org.linketinder.controller.VagasController
import org.linketinder.model.Empresa
import org.linketinder.model.Vagas

class VagasView {
    Scanner scanner = new Scanner(System.in)
    VagasController controller = new VagasController()

    VagasView(VagasController controller) {
        this.controller = controller
    }

    void exibirMenu() {
        while (true) {
            println "\n=== MENU DE VAGAS ==="
            println "1 - Adicionar Vaga"
            println "2 - Listar Vagas"
            println "3 - Remover Vaga"
            println "4 - Voltar"
            print "Escolha uma opção: "

            int opcao = scanner.nextInt()
            scanner.nextLine()

            switch (opcao) {
                case 1:
                    adicionarVaga()
                    break
                case 2:
                    listarVagas()
                    break
                case 3:
                    removerVaga()
                    break
                case 4:
                    return
                default:
                    println "❌ Opção inválida!"
            }
        }
    }

    void listarVagas() {
        List<Vagas> vagas = controller.listarVagas()

        if (vagas.isEmpty()) {
            println "⚠️ Nenhuma vaga encontrada."
            return
        }

        println "\n=== LISTA DE VAGAS ==="
        vagas.each { vaga ->
            println "🔹 Nome: ${vaga.nome}"
            println "   Descrição: ${vaga.descricao}"
            println "   Empresa: ${vaga.empresa.nome}"
            println "-------------------------"
        }
    }

    void adicionarVaga() {
        println "Digite o nome da vaga: "
        String nome = scanner.nextLine()
        println "Digite a descrição da vaga: "
        String descricao = scanner.nextLine()

        println "Digite o nome da empresa: "
        String nomeEmpresa = scanner.nextLine()

        Empresa empresa = new Empresa(nome: nomeEmpresa)

        boolean sucesso = controller.adicionarVaga(nome, descricao, empresa)
        if (sucesso) {
            println "✅ Vaga cadastrada com sucesso!"
        } else {
            println "❌ Erro ao cadastrar a vaga."
        }
    }

    void removerVaga() {
        println "Digite o id da vaga para remover: "
        int id = scanner.nextInt()

        boolean sucesso = controller.apagarVaga(id)
        if (sucesso) {
            println "✅ Vaga removida com sucesso!"
        } else {
            println "❌ Erro ao remover a vaga."
        }
    }
}
