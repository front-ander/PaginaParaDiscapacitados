package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/wev";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection con = null;

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conexión establecida.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: No se pudo establecer la conexión con la base de datos.");
            e.printStackTrace();
        }
        return con;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.err.println("Error: No se pudo cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
}

