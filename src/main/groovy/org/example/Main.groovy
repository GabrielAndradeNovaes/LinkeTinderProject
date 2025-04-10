package org.example

import org.example.controller.*
import org.example.view.*
import org.example.Dao.*
import java.io.BufferedReader
import java.io.InputStreamReader

static void main(String[] args) {
    try {
        ConnectionFactory.getConnection()
        println "Conexão com o banco de dados estabelecida com sucesso."
    } catch (Exception e) {
        println "❌ Erro ao conectar com o banco de dados: ${e.message}"
        System.exit(1)
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))

    // Repositórios
    CandidatoRepository candidatoRepository = new CandidatoRepository()
    EmpresaRepository empresaRepository = new EmpresaRepository()
    VagasRepository vagasRepository = new VagasRepository()
    CompetenciaRepository competenciaRepository = new CompetenciaRepository()

    // Controllers
    CandidatoController candidatoController = new CandidatoController(candidatoRepository)
    EmpresaController empresaController = new EmpresaController(empresaRepository)
    VagasController vagasController = new VagasController(vagasRepository)
    CompetenciaController competenciaController = new CompetenciaController(competenciaRepository)

    // Views
    CandidatoView candidatoView = new CandidatoView(candidatoController)
    EmpresaView empresaView = new EmpresaView(empresaController)
    VagasView vagasView = new VagasView(vagasController)
    CompetenciaView competenciaView = new CompetenciaView(competenciaController)

    println "=== Bem-vindo ao Sistema de Recrutamento ==="

    while (true) {
        println "\nEscolha uma opção:"
        println "1 - Gerenciar Candidatos"
        println "2 - Gerenciar Empresas"
        println "3 - Gerenciar Vagas"
        println "4 - Gerenciar Competências"
        println "5 - Sair"
        print "Opção: "

        try {
            Thread.sleep(100)
            String input = reader.readLine()?.trim()

            if (input == null) {
                println "Entrada inválida. Saindo..."
                break
            }

            if (input.isEmpty() || !input.matches("\\d+")) {
                println "❌ Entrada inválida. Por favor, insira um número."
                continue
            }

            int opcao = Integer.parseInt(input)

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
                    println "Tem certeza que deseja sair? (s/n)"
                    String resposta = reader.readLine()?.trim()?.toLowerCase()
                    if (resposta == 's') {
                        println "Saindo..."
                        System.exit(0)
                    }
                    break
                default:
                    println "❌ Opção inválida!"
            }
        } catch (Exception e) {
            println "❌ Erro: ${e.message}"
        }
    }
}
