package org.adat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String dbName = "adat_db";
    private static final String user = "postgres";
    private static final String pass = "1234";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the PostgreSQL server successfully.");

            String createDatabase = "CREATE DATABASE " + dbName;
            Statement stmt = conn.createStatement();
            stmt.execute(createDatabase);
            System.out.println("Database created successfully.");
            // Connect to the newly created database
            String dbUrl = url + dbName;
            conn = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connected to the new database successfully.");

            stmt = conn.createStatement();
            String createTabla = "CREATE TABLE IF NOT EXISTS alumnos (id SERIAL PRIMARY KEY, nombre VARCHAR(100), apellido VARCHAR(100), curso VARCHAR(100), DNI VARCHAR(10))";
            stmt.execute(createTabla);
            System.out.println("Table created successfully.");

            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
