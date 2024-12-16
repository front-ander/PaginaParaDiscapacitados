package DAO;

import Logica.Mensaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MensajeDAO {

    // Método para guardar un mensaje
    public boolean guardarMensaje(Mensaje mensaje) {
        String query = "INSERT INTO mensajes_contacto (id, nombre, correo, mensaje) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, mensaje.getId());
            ps.setString(2, mensaje.getNombre());
            ps.setString(3, mensaje.getCorreo());
            ps.setString(4, mensaje.getMensaje());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para obtener todos los mensajes
    public List<Mensaje> obtenerMensajes() {
        List<Mensaje> mensajes = new ArrayList<>();
        String query = "SELECT * FROM mensajes_contacto";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(rs.getString("id"));
                mensaje.setNombre(rs.getString("nombre"));
                mensaje.setCorreo(rs.getString("correo"));
                mensaje.setMensaje(rs.getString("mensaje"));
                mensajes.add(mensaje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensajes;
    }

    // Método para eliminar un mensaje por ID
    public boolean eliminarMensaje(int id) {
        String query = "DELETE FROM mensajes_contacto WHERE id = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }
    public String obtenerUltimoIdProducto() {
    String ultimoId = null;
    String query = "SELECT ID FROM mensajes_contacto ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {
            ultimoId = rs.getString("ID");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ultimoId;
}
}
