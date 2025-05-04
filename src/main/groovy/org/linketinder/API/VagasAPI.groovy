package org.linketinder.API

import com.sun.net.httpserver.HttpServer
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpExchange
import org.linketinder.controller.VagasController
import org.linketinder.model.Vagas
import org.linketinder.Dao.VagasRepository
import org.linketinder.model.Empresa
import com.fasterxml.jackson.databind.ObjectMapper

import java.net.InetSocketAddress

class VagasAPI {

    static void main(String[] args) {
        int PORT = 8000

        VagasRepository repo = new VagasRepository()
        VagasController controller = new VagasController(repo)
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0)

        server.createContext("/vagas", new HttpHandler() {
            void handle(HttpExchange exchange) {
                String method = exchange.requestMethod
                String path = exchange.requestURI.path
                String[] pathParts = path.split("/")
                String response = ""
                exchange.responseHeaders.set("Content-Type", "application/json")

                try {
                    if (method == "GET" && pathParts.length == 3) {
                        int id = pathParts[2].toInteger()
                        println("GET /vagas/${id} chamado")

                        Vagas vaga = controller.buscarVaga(id)
                        if (vaga != null) {
                            response = new ObjectMapper().writeValueAsString(vaga)
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        } else {
                            response = '{"mensagem": "Vaga não encontrada"}'
                            exchange.sendResponseHeaders(404, response.bytes.length)
                        }

                    } else if (method == "DELETE" && pathParts.length == 3) {
                        int id = pathParts[2].toInteger()
                        println("DELETE /vagas/${id} chamado")

                        boolean sucesso = controller.apagarVaga(id)
                        response = sucesso
                                ? '{"mensagem": "Vaga removida com sucesso"}'
                                : '{"mensagem": "Vaga não encontrada"}'
                        exchange.sendResponseHeaders(sucesso ? 200 : 404, response.bytes.length)

                    } else if (method == "GET" && pathParts.length == 2) {
                        println("GET /vagas chamado")
                        List<Vagas> vagas = controller.listarVagas()

                        if (vagas.isEmpty()) {
                            response = '{"mensagem": "Nenhuma vaga encontrada"}'
                            exchange.sendResponseHeaders(404, response.bytes.length)
                        } else {
                            response = new ObjectMapper().writeValueAsString(vagas)
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        }

                    } else if (method == "POST") {
                        String body = exchange.requestBody.text
                        ObjectMapper objectMapper = new ObjectMapper()
                        Map<String, String> json = objectMapper.readValue(body, Map)

                        Empresa empresa = new Empresa(
                                nome: "Empresa X",
                                email: "empresa@example.com",
                                cnpj: "12345678000195",
                                pais: "Brasil",
                                cep: "12345678"
                        )

                        boolean sucesso = controller.adicionarVaga(
                                json.get("nome"),
                                json.get("descricao"),
                                empresa
                        )

                        response = '{"sucesso": ' + sucesso + '}'
                        exchange.sendResponseHeaders(sucesso ? 201 : 500, response.bytes.length)

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
        println "Servidor rodando em http://localhost:${PORT}/vagas"
    }
}
