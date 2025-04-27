package org.linketinder.view

import org.linketinder.model.Candidato
import org.linketinder.controller.CandidatoController
import org.linketinder.model.Competencia
import org.linketinder.controller.MatchController


class CandidatoView {
    Scanner scanner = new Scanner(System.in)
    CandidatoController controller = new CandidatoController()
    MatchController matchController = new MatchController()

    CandidatoView(CandidatoController controller) {
        this.controller = controller
        this.matchController = matchController
    }

    void exibirMenu() {
        while (true) {
            println "\n=== MENU ==="
            println "1 - Adicionar Candidato"
            println "2 - Listar Todos os Candidatos"
            println "3 - Buscar Candidato por ID"
            println "4 - Apagar Candidato"
            println "5 - Curtir empresa"
            println "6 - Sair"
            print "Escolha uma opção: "

            int opcao = scanner.nextInt()
            scanner.nextLine()

            switch (opcao) {
                case 1:
                    coletarDadosCandidato()
                    break
                case 2:
                    listarTodosCandidatos()
                    break
                case 3:
                    buscarCandidatoPorId()
                    break
                case 4:
                    apagarCandidato()
                    break
                case 5:
                    curtirEmpresa()
                    break
                case 6:
                    println "Saindo..."
                    System.exit(0)
                default:
                    println "❌ Opção inválida!"
            }
        }
    }

    void coletarDadosCandidato() {
        println "Nome: "
        String nome = scanner.nextLine()
        println "Email: "
        String email = scanner.nextLine()
        println "CPF: "
        String cpf = scanner.nextLine()
        println "Idade: "
        int idade = scanner.nextInt()
        scanner.nextLine()
        println "Estado: "
        String estado = scanner.nextLine()
        println "CEP: "
        String cep = scanner.nextLine()

        List<Competencia> competencias = []
        println "Quantas competências deseja adicionar?"
        int qtd = scanner.nextInt()
        scanner.nextLine()

        for (int i = 0; i < qtd; i++) {
            println "Digite o nome da competência ${i + 1}:"
            String nomeComp = scanner.nextLine()
            competencias.add(new Competencia(nomeComp))
        }
        Candidato candidato = new Candidato(nome, email, cpf, idade, cep, estado, competencias)
        boolean sucesso = controller.adicionarCliente(candidato)

        println sucesso ? "✅ Cliente cadastrado com sucesso!" : "❌ Erro ao cadastrar cliente."
    }

    void listarTodosCandidatos() {
        List<Candidato> candidatos = controller.listarCandidatos()
        if (candidatos.isEmpty()) {
            println "⚠ Nenhum candidato cadastrado!"
        } else {
            candidatos.each { println it }
        }
    }

    void buscarCandidatoPorId() {
        println "Digite o ID do candidato: "
        int id = scanner.nextInt()
        scanner.nextLine()

        Candidato candidato = controller.buscarCandidato(id)
        println candidato ?: "⚠ Candidato não encontrado."
    }

    void curtirEmpresa() {
        println "Digite o ID do candidato que está curtindo:"
        Long idCandidato = scanner.nextLong()
        scanner.nextLine()
        println "Digite o ID da empresa que deseja curtir:"
        Long idEmpresa = scanner.nextLong()
        scanner.nextLine()
        matchController.curtirComoCandidato(idCandidato, idEmpresa)
        println "❤️ Candidato $idCandidato curtiu a empresa $idEmpresa"
    }

    void apagarCandidato() {
        println "Digite o ID do candidato para apagar: "
        int id = scanner.nextInt()
        scanner.nextLine()

        boolean sucesso = controller.apagarCandidato(id)
        println sucesso ? "✅ Candidato removido com sucesso!" : "❌ Erro ao remover candidato."
    }
}
