package org.linketinder

import org.linketinder.Dao.*
import org.linketinder.config.AppConfig

static void main(String[] args) {

    try {
        ConnectionFactory.getConnection()
        println "Conexão com o banco de dados estabelecida com sucesso."
    } catch (Exception e) {
        println "❌ Erro ao conectar com o banco de dados: ${e.message}"
        System.exit(1)
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))

    AppConfig appConfig = new AppConfig()

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
                    appConfig.candidatoView.exibirMenu()
                    break
                case 2:
                    appConfig.empresaView.exibirMenu()
                    break
                case 3:
                    appConfig.vagasView.exibirMenu()
                    break
                case 4:
                    appConfig.competenciaView.exibirMenu()
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
