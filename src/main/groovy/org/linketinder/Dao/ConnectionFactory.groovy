package org.linketinder.Dao

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionFactory {
    static String url = "jdbc:postgresql://localhost:5432/Teste"
    static String user = "geek"
    static String password = "152690"

    static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver")
            return DriverManager.getConnection(url, user, password)
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace()
            return null
        }
    }
}
