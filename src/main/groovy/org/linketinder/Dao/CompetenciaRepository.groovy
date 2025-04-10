package org.linketinder.Dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import org.linketinder.model.Competencia

class CompetenciaRepository {
    Connection con

    CompetenciaRepository() {
        this.con = ConnectionFactory.getConnection()
    }

    void inserir(Competencia competencia) {
        String sql = "INSERT INTO competencias (nome) VALUES (?)"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setString(1, competencia.nome)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    List<Competencia> listarTodas() {
        String sql = "SELECT * FROM competencias"
        List<Competencia> competencias = []
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {
                competencias.add(new Competencia(rs.getString("nome")))
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return competencias
    }

    void apagar(String nome) {
        String sql = "DELETE FROM competencias WHERE nome = ?"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setString(1, nome)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }
}
