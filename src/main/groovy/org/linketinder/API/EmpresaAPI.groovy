package org.linketinder.API

import com.sun.net.httpserver.HttpServer
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpExchange
import org.linketinder.controller.EmpresaController
import org.linketinder.model.Competencia
import org.linketinder.Dao.EmpresaRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.type.TypeReference

import java.net.InetSocketAddress

class EmpresaAPI {

    static void main(String[] args) {
        int PORT = 8001

        EmpresaRepository repo = new EmpresaRepository()
        EmpresaController controller = new EmpresaController(repo)
        ObjectMapper objectMapper = new ObjectMapper()

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0)

        server.createContext("/empresas", new HttpHandler() {
            @Override
            void handle(HttpExchange exchange) {
                String method = exchange.requestMethod
                String path = exchange.requestURI.path
                String[] pathParts = path.split("/")
                String response = ""
                exchange.responseHeaders.set("Content-Type", "application/json")

                try {
                    if (method == "GET" && pathParts.length == 2) {
                        println("GET /empresas chamado")
                        def empresas = controller.listarEmpresas()
                        if (empresas.isEmpty()) {
                            response = '{"mensagem": "Nenhuma empresa encontrada"}'
                            exchange.sendResponseHeaders(404, response.bytes.length)
                        } else {
                            response = objectMapper.writeValueAsString(empresas)
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        }

                    } else if (method == "GET" && pathParts.length == 3) {
                        int id = pathParts[2].toInteger()
                        println("GET /empresas/${id} chamado")
                        def empresa = controller.buscarEmpresa(id)
                        if (empresa != null) {
                            response = objectMapper.writeValueAsString(empresa)
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        } else {
                            response = '{"mensagem": "Empresa não encontrada"}'
                            exchange.sendResponseHeaders(404, response.bytes.length)
                        }

                    } else if (method == "POST") {
                        String body = exchange.requestBody.text
                        Map<String, Object> json = objectMapper.readValue(body, Map)

                        String nome = json.get("nome") as String
                        String email = json.get("email") as String
                        String cnpj = json.get("cnpj") as String
                        String pais = json.get("pais") as String
                        String cep = json.get("cep") as String

                        List<Competencia> competencias = objectMapper.convertValue(
                                json.get("competencias"),
                                new TypeReference<List<Competencia>>() {}
                        )

                        boolean sucesso = controller.adicionarEmpresa(nome, email, cnpj, pais, cep, competencias)
                        response = '{"sucesso": ' + sucesso + '}'
                        exchange.sendResponseHeaders(sucesso ? 201 : 500, response.bytes.length)

                    } else if (method == "DELETE" && pathParts.length == 3) {
                        int id = pathParts[2].toInteger()
                        println("DELETE /empresas/${id} chamado")

                        boolean sucesso = controller.apagarEmpresa(id)
                        if (sucesso) {
                            response = '{"mensagem": "Empresa removida com sucesso"}'
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        } else {
                            response = '{"mensagem": "Empresa não encontrada"}'
                            exchange.sendResponseHeaders(404, response.bytes.length)
                        }

                    } else {
                        response = '{"erro": "Método não suportado"}'
                        exchange.sendResponseHeaders(405, response.bytes.length)
                    }

                    exchange.responseBody.withWriter("UTF-8") { writer ->
                        writer << response
                        writer.flush()
                    }
                    exchange.close()

                } catch (Exception e) {
                    e.printStackTrace()
                    println("Erro ao processar a requisição: ${e.message}")
                    response = '{"erro": "Erro interno do servidor"}'
                    exchange.sendResponseHeaders(500, response.bytes.length)
                    exchange.responseBody.withWriter("UTF-8") { writer ->
                        writer << response
                        writer.flush()
                    }
                    exchange.close()
                }
            }
        })

        server.start()
        println "Servidor rodando em http://localhost:${PORT}/empresas"
    }
}
