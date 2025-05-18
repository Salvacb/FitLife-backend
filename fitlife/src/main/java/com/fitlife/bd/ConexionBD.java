package com.fitlife.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private static final String URL = "jdbc:h2:file:/home/salva/fitlife_db";
    private static final String USER = "sa";
    private static final String PASS = "";

    static {
        try {
            // Registrar el driver manualmente
            Class.forName("org.h2.Driver");
    
            // Crear tabla si no existe
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String sql = """
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100),
                    email VARCHAR(100) UNIQUE,
                    password VARCHAR(100),
                    edad INT,
                    peso DOUBLE,
                    altura DOUBLE,
                    nivel_actividad VARCHAR(50),
                    objetivo VARCHAR(100)
                )
            """;
            stmt.execute(sql);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
