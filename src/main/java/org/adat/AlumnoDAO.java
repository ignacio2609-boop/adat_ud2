package org.adat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlumnoDAO {

    private Connection conn;

    public AlumnoDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(String alumno) {
        String[] parts = alumno.replace("(", "").replace(")", "").replace("'", "").split(", ");
        String dni = parts[3];

        String checkQuery = "SELECT COUNT(*) FROM alumnos WHERE dni = '" + dni + "'";
        String instQuery = "INSERT INTO alumnos (nombre, apellido, curso, dni) VALUES " + alumno;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(checkQuery);
            rs.next();
            if (rs.getInt(1) == 0) {
                stmt.execute(instQuery);
            } else {
                System.out.println("Alumno con DNI " + dni + " ya existe.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
