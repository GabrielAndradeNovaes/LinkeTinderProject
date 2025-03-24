package org.example.Dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import org.example.model.Candidato

class CandidatoRepository {

    Connection con

    CandidatoRepository() {
        this.con = ConnectionFactory.getConnection()
    }

    void inserir(Candidato candidato) {
        String sql = "INSERT INTO candidato (nome, email, cpf, idade, estado, cep) VALUES (?, ?, ?, ?, ?, ?)"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.with {
                setString(1, candidato.nome)
                setString(2, candidato.email)
                setString(3, candidato.cpf)
                setInt(4, candidato.idade)
                setString(5, candidato.estado)
                setString(6, candidato.cep)
                executeUpdate()
            }
            stmt.close()
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
