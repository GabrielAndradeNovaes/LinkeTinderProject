package org.linketinder.Dao

import org.linketinder.Dao.interfaces.ICandidatoRepository

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import org.linketinder.model.Candidato

class CandidatoRepository implements ICandidatoRepository{

    private final Connection con

    CandidatoRepository() {
        this.con = ConnectionFactory.getConnection()
    }

    void inserir(Candidato candidato) {
        String sql = "INSERT INTO candidato (nome, email, cpf, idade, estado, cep) VALUES (?, ?, ?, ?, ?, ?) RETURNING id"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.with {
                setString(1, candidato.nome)
                setString(2, candidato.email)
                setString(3, candidato.cpf)
                setInt(4, candidato.idade)
                setString(5, candidato.estado)
                setString(6, candidato.cep)
            }

            ResultSet rs = stmt.executeQuery()
            Long idCandidato = null
            if (rs.next()) {
                idCandidato = rs.getLong("id")
            }
            rs.close()
            stmt.close()

            candidato.competencias.each { competencia ->
                inserirCompetenciaParaCandidato(idCandidato, competencia.nome)
            }

        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    void inserirCompetenciaParaCandidato(Long idCandidato, String nomeCompetencia) {
        try {
            String buscarSql = "SELECT id FROM competencias WHERE nome = ?"
            PreparedStatement buscarStmt = con.prepareStatement(buscarSql)
            buscarStmt.setString(1, nomeCompetencia)
            ResultSet rs = buscarStmt.executeQuery()

            Long idCompetencia
            if (rs.next()) {
                idCompetencia = rs.getLong("id")
            } else {
                String inserirSql = "INSERT INTO competencias (nome) VALUES (?) RETURNING id"
                PreparedStatement inserirStmt = con.prepareStatement(inserirSql)
                inserirStmt.setString(1, nomeCompetencia)
                ResultSet rsInsert = inserirStmt.executeQuery()
                if (rsInsert.next()) {
                    idCompetencia = rsInsert.getLong("id")
                }
                rsInsert.close()
                inserirStmt.close()
            }
            rs.close()
            buscarStmt.close()

            String relacaoSql = "INSERT INTO candidatos_competencias (id_candidato, id_competencia) VALUES (?, ?)"
            PreparedStatement relacaoStmt = con.prepareStatement(relacaoSql)
            relacaoStmt.setLong(1, idCandidato)
            relacaoStmt.setLong(2, idCompetencia)
            relacaoStmt.executeUpdate()
            relacaoStmt.close()

        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    Candidato listarUm(int id) {
        String sql = "SELECT * FROM candidato WHERE id = ?"
        Candidato candidato = null
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setInt(1, id)
            ResultSet rs = stmt.executeQuery()

            if (rs.next()) {
                candidato = new Candidato(
                        nome: rs.getString("nome"),
                        email: rs.getString("email"),
                        cpf: rs.getString("cpf"),
                        idade: rs.getInt("idade"),
                        cep: rs.getString("cep"),
                        estado: rs.getString("estado")

                )
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return candidato
    }

    List<Candidato> listarTodos() {
        String sql = "SELECT * FROM candidato"
        List<Candidato> candidatos = []
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {
                candidatos.add(new Candidato(
                        nome: rs.getString("nome"),
                        cpf: rs.getString("cpf"),
                        email: rs.getString("email"),
                        idade: rs.getInt("idade"),
                        cep: rs.getString("cep"),
                        estado: rs.getString("estado")
                ))
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return candidatos
    }

    void apagar(int id) {
        String sql = "DELETE FROM candidato WHERE id = ?"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setInt(1, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}
