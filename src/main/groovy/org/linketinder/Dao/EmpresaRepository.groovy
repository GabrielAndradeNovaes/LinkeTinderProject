package org.linketinder.Dao

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import org.linketinder.model.Empresa
import org.linketinder.model.Competencia

class EmpresaRepository {
    Connection con

    EmpresaRepository() {
        this.con = ConnectionFactory.getConnection()
    }

    void inserir(Empresa empresa) {
        String sql = "INSERT INTO empresa (nome, email, cnpj, pais, cep) VALUES (?, ?, ?, ?, ?)"
        try {
            PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
            stmt.with {
                setString(1, empresa.nome)
                setString(2, empresa.email)
                setString(3, empresa.cnpj)
                setString(4, empresa.pais)
                setString(5, empresa.cep)
                executeUpdate()
            }

            ResultSet rs = stmt.getGeneratedKeys()
            if (rs.next()) {
                int empresaId = rs.getInt(1)
                inserirCompetencias(empresaId, empresa.competencias)
            }

            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    private void inserirCompetencias(int empresaId, List<Competencia> competencias) {
        if (competencias.isEmpty()) return

        String sql = "INSERT INTO empresa_competencia (empresa_id, nome) VALUES (?, ?)"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            competencias.each { competencia ->
                stmt.setInt(1, empresaId)
                stmt.setString(2, competencia.nome)
                stmt.addBatch()
            }
            stmt.executeBatch()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    Empresa listarUma(int id) {
        String sql = "SELECT * FROM empresa WHERE id = ?"
        Empresa empresa = null
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setInt(1, id)
            ResultSet rs = stmt.executeQuery()

            if (rs.next()) {
                empresa = new Empresa(
                        id: rs.getInt("id"),
                        nome: rs.getString("nome"),
                        email: rs.getString("email"),
                        cnpj: rs.getString("cnpj"),
                        pais: rs.getString("pais"),
                        cep: rs.getString("cep"),
                        competencias: buscarCompetencias(rs.getInt("id"))
                )
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return empresa
    }

    List<Empresa> listarTodas() {
        String sql = "SELECT * FROM empresa"
        List<Empresa> empresas = []
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {
                int empresaId = rs.getInt("id")
                empresas.add(new Empresa(
                        nome: rs.getString("nome"),
                        email: rs.getString("email"),
                        cnpj: rs.getString("cnpj"),
                        pais: rs.getString("pais"),
                        cep: rs.getString("cep"),
                        competencias: buscarCompetencias(empresaId)
                ))
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return empresas
    }

    private List<Competencia> buscarCompetencias(int empresaId) {
        String sql = "SELECT nome FROM empresa_competencia WHERE empresa_id = ?"
        List<Competencia> competencias = []
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setInt(1, empresaId)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {
                competencias.add(new Competencia(nome: rs.getString("nome")))
            }
            rs.close()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return competencias
    }

    void apagar(int id) {
        String sql = "DELETE FROM empresa WHERE id = ?"
        try {
            apagarCompetencias(id)
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setInt(1, id)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    private void apagarCompetencias(int empresaId) {
        String sql = "DELETE FROM empresa_competencia WHERE empresa_id = ?"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setInt(1, empresaId)
            stmt.executeUpdate()
            stmt.close()
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

}
