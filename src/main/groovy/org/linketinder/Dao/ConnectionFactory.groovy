package org.linketinder.Dao

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionFactory {

    private static Connection connection

    private static String dbType = "postgresql"
    private static String url = "jdbc:postgresql://localhost:5432/Teste"
    private static String user = "geek"
    private static String password = "152690"

    private ConnectionFactory() {}

    static void configureDatabase(String tipoBanco, String novaUrl, String novoUsuario, String novaSenha) {
        dbType = tipoBanco
        url = novaUrl
        user = novoUsuario
        password = novaSenha
    }

    static Connection getConnection() {
        if (connection == null || connection.isClosed()) {
            try {
                carregarDriver()
                connection = DriverManager.getConnection(url, user, password)
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace()
            }
        }
        return connection
    }

    private static void carregarDriver() throws ClassNotFoundException {
        switch (dbType.toLowerCase()) {
            case "postgresql":
                Class.forName("org.postgresql.Driver")
                break
            case "mysql":
                Class.forName("com.mysql.cj.jdbc.Driver")
                break
            case "sqlite":
                Class.forName("org.sqlite.JDBC")
                break
            default:
                throw new IllegalArgumentException("Banco de dados não suportado: $dbType")
        }
    }
}
