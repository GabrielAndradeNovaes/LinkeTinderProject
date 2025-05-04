package org.linketinder.API

import com.sun.net.httpserver.HttpServer
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpExchange
import org.linketinder.controller.CompetenciaController
import org.linketinder.model.Competencia
import org.linketinder.Dao.CompetenciaRepository
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.InetSocketAddress

class CompetenciaAPI {

    static void main(String[] args) {
        int PORT = 8000

        CompetenciaRepository repo = new CompetenciaRepository()
        CompetenciaController controller = new CompetenciaController(repo)

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0)

        server.createContext("/competencias", new HttpHandler() {
            @Override
            void handle(HttpExchange exchange) {
                String method = exchange.requestMethod
                String path = exchange.requestURI.path
                String[] pathParts = path.split("/")
                String response = ""
                exchange.responseHeaders.set("Content-Type", "application/json")

                try {
                    if (method == "GET" && pathParts.length == 2) {
                        println("GET /competencias chamado")
                        List<Competencia> competencias = controller.listarCompetencias()
                        if (competencias.isEmpty()) {
                            response = '{"mensagem": "Nenhuma competência encontrada"}'
                            exchange.sendResponseHeaders(404, response.bytes.length)
                        } else {
                            ObjectMapper mapper = new ObjectMapper()
                            response = mapper.writeValueAsString(competencias)
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        }

                    } else if (method == "GET" && pathParts.length == 3) {
                        int id = pathParts[2].toInteger()
                        println("GET /competencias/${id} chamado")

                        Competencia competencia = controller.buscarCompetencia(id)
                        if (competencia != null) {
                            ObjectMapper mapper = new ObjectMapper()
                            response = mapper.writeValueAsString(competencia)
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        } else {
                            response = '{"mensagem": "Competência não encontrada"}'
                            exchange.sendResponseHeaders(404, response.bytes.length)
                        }

                    } else if (method == "POST") {
                        String body = exchange.requestBody.text
                        ObjectMapper objectMapper = new ObjectMapper()
                        Map<String, Object> json = objectMapper.readValue(body, Map)

                        String nome = json.get("nome") as String
                        boolean sucesso = controller.adicionarCompetencia(nome)
                        response = '{"sucesso": ' + sucesso + '}'
                        exchange.sendResponseHeaders(sucesso ? 201 : 500, response.bytes.length)

                    } else if (method == "DELETE" && pathParts.length == 3) {
                        int id = pathParts[2].toInteger()
                        println("DELETE /competencias/${id} chamado")

                        boolean sucesso = controller.apagarCompetencia(id)
                        if (sucesso) {
                            response = '{"mensagem": "Competência removida com sucesso"}'
                            exchange.sendResponseHeaders(200, response.bytes.length)
                        } else {
                            response = '{"mensagem": "Competência não encontrada"}'
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
        println "Servidor rodando em http://localhost:${PORT}/competencias"
    }
}
