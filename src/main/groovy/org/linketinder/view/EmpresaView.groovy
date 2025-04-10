package org.linketinder.view

import org.linketinder.controller.*
import org.linketinder.model.Empresa
import org.linketinder.model.Competencia

class EmpresaView {
    Scanner scanner = new Scanner(System.in)
    EmpresaController controller = new EmpresaController()

    EmpresaView(EmpresaController controller) {
        this.controller = controller
    }

    void exibirMenu() {
        while (true) {
            println "\n=== MENU EMPRESA ==="
            println "1 - Adicionar Empresa"
            println "2 - Listar Todas as Empresas"
            println "3 - Buscar Empresa por ID"
            println "4 - Apagar Empresa"
            println "5 - Sair"
            print "Escolha uma opção: "

            int opcao = scanner.nextInt()
            scanner.nextLine()

            switch (opcao) {
                case 1:
                    coletarDadosEmpresa()
                    break
                case 2:
                    listarTodasEmpresas()
                    break
                case 3:
                    buscarEmpresaPorId()
                    break
                case 4:
                    apagarEmpresa()
                    break
                case 5:
                    println "Saindo..."
                    System.exit(0)
                default:
                    println "❌ Opção inválida!"
            }
        }
    }

    void coletarDadosEmpresa() {
        println "Nome da empresa: "
        String nome = scanner.nextLine()
        println "Email: "
        String email = scanner.nextLine()
        println "CNPJ: "
        String cnpj = scanner.nextLine()
        println "País: "
        String pais = scanner.nextLine()
        println "CEP: "
        String cep = scanner.nextLine()

        List<Competencia> competencias = coletarCompetencias()
        boolean sucesso = controller.adicionarEmpresa(nome, email, cnpj, pais, cep, competencias)

        if (sucesso) {
            println "✅ Empresa cadastrada com sucesso!"
        } else {
            println "❌ Erro ao cadastrar a empresa."
        }
    }

    List<Competencia> coletarCompetencias() {
        List<Competencia> competencias = []

        while (true) {
            println "Digite uma competência (ou pressione ENTER para finalizar):"
            String nomeCompetencia = scanner.nextLine()

            if (nomeCompetencia.isEmpty()) {
                break
            }

            competencias.add(new Competencia(nome: nomeCompetencia))
        }

        return competencias
    }

    void listarTodasEmpresas() {
        List<Empresa> empresas = controller.listarEmpresas()
        if (empresas.isEmpty()) {
            println "⚠ Nenhuma empresa cadastrada!"
        } else {
            empresas.each { empresa ->
                println "🏢 ID: ${empresa.id} | Nome: ${empresa.nome} | Email: ${empresa.email} | País: ${empresa.pais} | CEP: ${empresa.cep}"
                println "   Competências: ${empresa.competencias.collect { it.nome }.join(', ')}\n"
            }
        }
    }

    void buscarEmpresaPorId() {
        println "Digite o ID da empresa: "
        int id = scanner.nextInt()
        scanner.nextLine()

        Empresa empresa = controller.buscarEmpresa(id)
        if (empresa) {
            println "🏢 ID: ${empresa.id} | Nome: ${empresa.nome} | Email: ${empresa.email} | País: ${empresa.pais} | CEP: ${empresa.cep}"
            println "   Competências: ${empresa.competencias.collect { it.nome }.join(', ')}\n"
        } else {
            println "⚠ Empresa não encontrada."
        }
    }

    void apagarEmpresa() {
        println "Digite o ID da empresa para apagar: "
        int id = scanner.nextInt()
        scanner.nextLine()

        boolean sucesso = controller.apagarEmpresa(id)
        if (sucesso) {
            println "✅ Empresa removida com sucesso!"
        } else {
            println "❌ Erro ao remover a empresa."
        }
    }
}
