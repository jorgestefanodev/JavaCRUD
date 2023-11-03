package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bd";

    // Conexão com o banco de dados
    public static Connection createConnectionMySql() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Recuperar uma conexão com o banco de dados
        Connection con = createConnectionMySql();
        if (con != null) {
            System.out.println("Conexão realizada com sucesso");
            con.close();
        }
    }
}