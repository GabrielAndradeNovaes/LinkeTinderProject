package org.linketinder.API

import com.sun.net.httpserver.HttpServer
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpExchange
import org.linketinder.controller.CandidatoController
import org.linketinder.model.Candidato
import org.linketinder.Dao.CandidatoRepository
import com.fasterxml.jackson.databind.ObjectMapper

import java.net.InetSocketAddress
import java.nio.charset.StandardCharsets

int PORT = 8000

CandidatoRepository repo = new CandidatoRepository()
CandidatoController controller = new CandidatoController(repo)

HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0)

server.createContext("/candidatos", new HttpHandler() {
    @Override
    void handle(HttpExchange exchange) {
        String method = exchange.getRequestMethod()
        String path = exchange.getRequestURI().getPath()
        String[] pathParts = path.split("/")

        String response = ""
        exchange.getResponseHeaders().set("Content-Type", "application/json")

        try {
            if (method == "GET" && pathParts.length == 3) {
                int id = Integer.parseInt(pathParts[2])
                println("GET /candidatos/${id} chamado")

                Candidato candidato = controller.buscarCandidato(id)
                if (candidato != null) {
                    response = new ObjectMapper().writeValueAsString(candidato)
                    exchange.sendResponseHeaders(200, response.getBytes().length)
                } else {
                    response = '{"mensagem": "Candidato não encontrado"}'
                    exchange.sendResponseHeaders(404, response.getBytes().length)
                }

            } else if (method == "DELETE" && pathParts.length == 3) {
                int id = Integer.parseInt(pathParts[2])
                println("DELETE /candidatos/${id} chamado")

                boolean sucesso = controller.apagarCandidato(id)
                response = sucesso
                        ? '{"mensagem": "Candidato removido com sucesso"}'
                        : '{"mensagem": "Candidato não encontrado"}'
                exchange.sendResponseHeaders(sucesso ? 200 : 404, response.getBytes().length)

            } else if (method == "GET" && pathParts.length == 2) {
                println("GET /candidatos chamado")
                List<Candidato> candidatos = controller.listarCandidatos()
                if (candidatos.isEmpty()) {
                    response = '{"mensagem": "Nenhum candidato encontrado"}'
                    exchange.sendResponseHeaders(404, response.getBytes().length)
                } else {
                    response = new ObjectMapper().writeValueAsString(candidatos)
                    exchange.sendResponseHeaders(200, response.getBytes().length)
                }

            } else if (method == "POST") {
                String body = exchange.getRequestBody().getText()
                ObjectMapper objectMapper = new ObjectMapper()
                Map<String, Object> json = objectMapper.readValue(body, Map)

                Candidato candidato = new Candidato(
                        nome: json.get("nome") as String,
                        email: json.get("email") as String,
                        cpf: json.get("cpf") as String,
                        idade: json.get("idade") as Integer,
                        estado: json.get("estado") as String,
                        cep: json.get("cep") as String,
                        competencias: json.get("competencias") as List<String>
                )

                boolean sucesso = controller.adicionarCliente(candidato)
                response = '{"sucesso": ' + sucesso + '}'
                exchange.sendResponseHeaders(sucesso ? 201 : 500, response.getBytes().length)

            } else {
                response = '{"erro": "Método não suportado"}'
                exchange.sendResponseHeaders(405, response.getBytes().length)
            }

            exchange.getResponseBody().withWriter(StandardCharsets.UTF_8.name()) { writer ->
                writer << response
                writer.flush()
            }
            exchange.close()

        } catch (Exception e) {
            e.printStackTrace()
            println("Erro ao processar a requisição: ${e.message}")
            response = '{"erro": "Erro interno do servidor"}'
            exchange.sendResponseHeaders(500, response.getBytes().length)
            exchange.getResponseBody().withWriter(StandardCharsets.UTF_8.name()) { writer ->
                writer << response
                writer.flush()
            }
            exchange.close()
        }
    }
})

server.start()
println "Servidor rodando em http://localhost:${PORT}/candidatos"
