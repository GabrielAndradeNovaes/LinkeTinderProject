package org.example

import org.example.model.*
import org.example.controller.*
import org.example.view.*
import org.example.Dao.*

static void main(String[] args) {

    try {
        ConnectionFactory.getConnection()
        println "Conexão com o banco de dados estabelecida com sucesso."
    } catch (Exception e) {
        println "❌ Erro ao conectar com o banco de dados: ${e.message}"
        System.exit(1)
    }
    ConnectionFactory.getConnection()

    CandidatoController candidatoController = new CandidatoController()
    EmpresaController empresaController = new EmpresaController()
    VagasController vagasController = new VagasController()
    CompetenciaController competenciaController = new CompetenciaController()

    CandidatoView candidatoView = new CandidatoView()
    EmpresaView empresaView = new EmpresaView()
    VagasView vagasView = new VagasView()
    CompetenciaView competenciaView = new CompetenciaView()

    println "=== Bem-vindo ao Sistema de Recrutamento ==="

    while (true) {
        println "\nEscolha uma opção:"
        println "1 - Gerenciar Candidatos"
        println "2 - Gerenciar Empresas"
        println "3 - Gerenciar vagas"
        println "4 - Gerenciar competencias"
        println "5 - Sair"
        print "Opção: "
        Scanner scanner = new Scanner(System.in)
        int opcao = scanner.nextInt()

        switch (opcao) {
            case 1:
                candidatoView.exibirMenu()
                break
            case 2:
                empresaView.exibirMenu()
                break
            case 3:
                vagasView.exibirMenu()
                break
            case 4:
                competenciaView.exibirMenu()
                break
            case 5:
                println "Saindo..."
                System.exit(0)
                break
            default:
                println "❌ Opção inválida!"
        }
    }
}
