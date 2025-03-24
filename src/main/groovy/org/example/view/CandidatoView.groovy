package org.example.view

import org.example.model.Candidato
import org.example.controller.CandidatoController

class CandidatoView {
    Scanner scanner = new Scanner(System.in)
    CandidatoController controller = new CandidatoController()

    void exibirMenu() {
        while (true) {
            println "\n=== MENU ==="
            println "1 - Adicionar Candidato"
            println "2 - Listar Todos os Candidatos"
            println "3 - Buscar Candidato por ID"
            println "4 - Apagar Candidato"
            println "5 - Sair"
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

        controller.adicionarCliente(nome, email, cpf, idade, cep, estado)
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

    void apagarCandidato() {
        println "Digite o ID do candidato para apagar: "
        int id = scanner.nextInt()
        scanner.nextLine()

        controller.apagarCandidato(id)
    }
}
