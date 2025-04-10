package org.linketinder.test

import org.linketinder.Dao.CompetenciaRepository
import org.linketinder.model.Competencia
import spock.lang.Specification

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

class CompetenciaRepositorySpec extends Specification {

    private Connection connectionMock
    private PreparedStatement preparedStatementMock
    private ResultSet resultSetMock
    private CompetenciaRepository competenciaRepository

    void setup() {
        connectionMock = Mock(Connection)
        preparedStatementMock = Mock(PreparedStatement)
        resultSetMock = Mock(ResultSet)
        competenciaRepository = new CompetenciaRepository()
        competenciaRepository.con = connectionMock
    }

    void "Deve inserir uma competencia com sucesso"() {
        given:
        Competencia competencia = new Competencia("Groovy")
        connectionMock.prepareStatement(_ as String) >> preparedStatementMock

        when:
        competenciaRepository.inserir(competencia)

        then:
        1 * preparedStatementMock.setString(1, "Groovy")
        1 * preparedStatementMock.executeUpdate()
        1 * preparedStatementMock.close()
        noExceptionThrown()
    }

    void "Deve retornar uma lista de competencias"() {
        given:
        connectionMock.prepareStatement(_ as String) >> preparedStatementMock
        preparedStatementMock.executeQuery() >> resultSetMock

        resultSetMock.next() >>> [true, false]
        resultSetMock.getString("nome") >> "Java"

        when:
        List<Competencia> competencias = competenciaRepository.listarTodas()

        then:
        competencias.size() == 1
        competencias[0].nome == "Java"
    }

    void "Deve apagar uma competencia pelo nome"() {
        given:
        String nome = "Groovy"
        connectionMock.prepareStatement(_ as String) >> preparedStatementMock

        when:
        competenciaRepository.apagar(nome)

        then:
        1 * preparedStatementMock.setString(1, nome)
        1 * preparedStatementMock.executeUpdate()
        1 * preparedStatementMock.close()
        noExceptionThrown()
    }
}
