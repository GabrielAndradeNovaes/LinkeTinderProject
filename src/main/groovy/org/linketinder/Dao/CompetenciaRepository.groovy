package org.linketinder.Dao

import org.linketinder.Dao.interfaces.ICompetenciaRepository

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import org.linketinder.model.Competencia

class CompetenciaRepository implements ICompetenciaRepository{
    private final Connection con

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

    Competencia listarUm(int id) {
        String sql = "SELECT * FROM competencias WHERE id = ?"
        Competencia competencia = null
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setInt(1, id)
            ResultSet rs = stmt.executeQuery()

            if (rs.next()) {
                competencia = new Competencia(rs.getString("nome"))
            }

            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return competencia
    }

    void apagar(int id) {
        String sql = "DELETE FROM competencias WHERE id = ?"
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
