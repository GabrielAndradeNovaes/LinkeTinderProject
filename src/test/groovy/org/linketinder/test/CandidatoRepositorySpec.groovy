package org.linketinder.test

import org.linketinder.model.Candidato
import org.linketinder.model.Competencia
import org.linketinder.Dao.CandidatoRepository
import spock.lang.Specification

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CandidatoRepositorySpec extends Specification {

    private Connection connectionMock
    private PreparedStatement preparedStatementMock
    private PreparedStatement preparedStatementMock2
    private PreparedStatement preparedStatementMock3
    private ResultSet resultSetMock
    private ResultSet resultSetMock2
    private CandidatoRepository candidatoRepository

    void setup() {
        connectionMock = Mock(Connection)
        preparedStatementMock = Mock(PreparedStatement)
        preparedStatementMock2 = Mock(PreparedStatement)
        preparedStatementMock3 = Mock(PreparedStatement)
        resultSetMock = Mock(ResultSet)
        resultSetMock2 = Mock(ResultSet)

        candidatoRepository = new CandidatoRepository()
        candidatoRepository.con = connectionMock
    }

    void "Deve inserir um candidato com sucesso"() {
        given: "Um candidato com uma competência e os mocks configurados"
        Candidato candidato = new Candidato(
                nome: "João Silva",
                email: "joao@email.com",
                cpf: "123.456.789-00",
                idade: 30,
                estado: "SP",
                cep: "01234-567",
                competencias: [new Competencia(nome: "Groovy")]
        )

        // Configura ordem de retorno dos mocks
        connectionMock.prepareStatement(_ as String) >>> [preparedStatementMock, preparedStatementMock2, preparedStatementMock3]

        // Simula retorno da inserção do candidato
        preparedStatementMock.executeQuery() >> resultSetMock
        resultSetMock.next() >> true
        resultSetMock.getLong("id") >> 1L

        // Simula busca por competência inexistente
        preparedStatementMock2.executeQuery() >> resultSetMock2
        resultSetMock2.next() >> false

        // Simula inserção de nova competência
        preparedStatementMock3.executeQuery() >> resultSetMock
        resultSetMock.next() >> true
        resultSetMock.getLong("id") >> 2L

        when: "O método inserir é chamado"
        candidatoRepository.inserir(candidato)

        then: "Nenhuma exceção é lançada"
        noExceptionThrown()
    }

    void "Deve retornar um candidato ao buscar por ID"() {
        given: "Um ID existente no banco"
        int idBuscado = 1

        connectionMock.prepareStatement(_ as String) >> preparedStatementMock
        preparedStatementMock.executeQuery() >> resultSetMock
        resultSetMock.next() >> true
        resultSetMock.getString("nome") >> "João Silva"
        resultSetMock.getString("email") >> "joao@email.com"
        resultSetMock.getString("cpf") >> "123.456.789-00"
        resultSetMock.getInt("idade") >> 30
        resultSetMock.getString("estado") >> "SP"
        resultSetMock.getString("cep") >> "01234-567"

        when: "O método listarUm é chamado"
        Candidato candidato = candidatoRepository.listarUm(idBuscado)

        then: "O candidato é retornado com os dados corretos"
        candidato.nome == "João Silva"
        candidato.email == "joao@email.com"
        candidato.cpf == "123.456.789-00"
        candidato.idade == 30
        candidato.estado == "SP"
        candidato.cep == "01234-567"
    }

    void "Deve retornar uma lista de candidatos"() {
        given: "Há um candidato no banco"
        connectionMock.prepareStatement(_ as String) >> preparedStatementMock
        preparedStatementMock.executeQuery() >> resultSetMock

        resultSetMock.next() >>> [true, false]
        resultSetMock.getString("nome") >> "Maria Oliveira"
        resultSetMock.getString("cpf") >> "987.654.321-00"
        resultSetMock.getString("email") >> "maria@email.com"
        resultSetMock.getInt("idade") >> 25
        resultSetMock.getString("estado") >> "RJ"
        resultSetMock.getString("cep") >> "76543-210"

        when: "O método listarTodos é chamado"
        List<Candidato> candidatos = candidatoRepository.listarTodos()

        then: "A lista contém os dados corretos"
        candidatos.size() == 1
        candidatos[0].nome == "Maria Oliveira"
        candidatos[0].cpf == "987.654.321-00"
        candidatos[0].email == "maria@email.com"
        candidatos[0].idade == 25
        candidatos[0].estado == "RJ"
        candidatos[0].cep == "76543-210"
    }

    void "Deve deletar um candidato pelo ID"() {
        given: "Um ID válido e o mock configurado"
        int id = 10
        connectionMock.prepareStatement(_ as String) >> preparedStatementMock
        preparedStatementMock.executeUpdate() >> 1

        when: "O método apagar é chamado"
        candidatoRepository.apagar(id)

        then: "Nenhuma exceção é lançada"
        noExceptionThrown()
    }
}
