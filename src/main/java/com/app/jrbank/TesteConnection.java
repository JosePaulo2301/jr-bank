package com.app.jrbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConnection {

    public static String url = "jdbc:postgresql://localhost:5432/jrbank";
    public static String user = "root";
    public static String password = "123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) throws SQLException {
        try (Connection conn = conectar()) {
            System.out.println("Conectado em: " + conn.getCatalog());
        }
    }
}
