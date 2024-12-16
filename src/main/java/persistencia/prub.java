/**package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/wev";
    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection con = null;

    public static void main(String[] args) {
        try {
            // Verifica si la conexión es nula o está cerrada
            if (con == null || con.isClosed()) {
                // Cargar el driver MySQL JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                // Establecer la conexión
                con = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conexión exitosa");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos");
            e.printStackTrace();
        } finally {
            // Cierra la conexión después de usarla
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Conexión cerrada");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}**/


