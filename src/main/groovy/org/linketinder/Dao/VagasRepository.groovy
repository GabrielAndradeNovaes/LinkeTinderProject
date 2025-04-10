package org.linketinder.Dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import org.linketinder.model.Vagas
import org.linketinder.model.Empresa

class VagasRepository {
    Connection con

    VagasRepository() {
        this.con = ConnectionFactory.getConnection()
    }

    void inserir(Vagas vaga) {
        int idEmpresa = (vaga.empresa != null && vaga.empresa.id > 0) ? vaga.empresa.id : 0
        String sql = "INSERT INTO vagas (nome, descricao, id_empresa) VALUES (?, ?, ?)"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setString(1, vaga.nome)
            stmt.setString(2, vaga.descricao)
            if (idEmpresa != null) {
                stmt.setInt(3, idEmpresa)
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER)
            }
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    List<Vagas> listarTodas() {
        String sql = "SELECT v.id, v.nome, v.descricao, e.id AS id_empresa, e.nome AS empresa_nome FROM vagas v JOIN empresa e ON v.id_empresa = e.id"
        List<Vagas> vagas = []
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {
                Empresa empresa = new Empresa(rs.getInt("id_empresa"), rs.getString("empresa_nome"))
                vagas.add(new Vagas(rs.getString("nome"), rs.getString("descricao"), empresa))
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return vagas
    }

    void apagar(String nome) {
        String sql = "DELETE FROM vagas WHERE nome = ?"
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
