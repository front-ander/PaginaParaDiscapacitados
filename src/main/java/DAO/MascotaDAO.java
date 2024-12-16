/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Logica.Mascota;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MascotaDAO {

   public boolean agregarMascota(Mascota mascota) {
        String query = "INSERT INTO mascota (ID, Nombre, Descripcion,DescripcionC, Precio,imagen, Tiempo, Calificacion, CategoriaM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, mascota.getID());
            stmt.setString(2, mascota.getNombre());
            stmt.setString(3, mascota.getDescripcion());
            stmt.setString(4, mascota.getDescripcionC());
            stmt.setDouble(5, mascota.getPrecio());
            stmt.setBytes(6, mascota.getImagen());
            stmt.setString(7, mascota.getTiempo());
            stmt.setString(8, mascota.getCalificacion());
            stmt.setString(9, mascota.getCategoria());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
          
        }  
        return false;
    }

    // Método para obtener todos los proveedores
    public List<Mascota> obtenerTodos() {
        List<Mascota> mascotaList = new ArrayList<>();
        String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.DescripcionC, p.Precio, p.imagen, p.Tiempo, p.Calificacion, c.Descripcion AS CategoriaNombre FROM mascota p JOIN categoriam c ON p.CategoriaM = c.ID";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mascota mascota = new Mascota();
                mascota.setID(rs.getString("ID"));
                mascota.setNombre(rs.getString("Nombre"));
                mascota.setDescripcion(rs.getString("Descripcion"));
                mascota.setDescripcionC(rs.getString("DescripcionC"));
                mascota.setPrecio(rs.getDouble("Precio"));
                mascota.setImagen(rs.getBytes("imagen"));
                mascota.setTiempo(rs.getString("Tiempo"));
                mascota.setCalificacion(rs.getString("Calificacion"));
                mascota.setCategoria(rs.getString("CategoriaNombre")); 
                mascotaList.add(mascota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener todos los ingredientes: " + e.getMessage());
        }

        return mascotaList;
    }

    // Método para obtener un proveedor por ID
    public Mascota obtenerPorId(String id) {
    Mascota mascota = null;
    String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.DescripcionC, p.Precio, p.imagen, p.Tiempo, p.Calificacion, c.Descripcion AS CategoriaNombre FROM mascota p JOIN categoriam c ON p.CategoriaM = c.ID WHERE p.ID = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        // Establecer el valor del parámetro de la consulta
        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Crear el objeto Producto
                mascota = new Mascota();
                mascota.setID(rs.getString("ID"));
                mascota.setNombre(rs.getString("Nombre"));
                mascota.setDescripcion(rs.getString("Descripcion"));
                mascota.setDescripcionC(rs.getString("DescripcionC"));
                mascota.setPrecio(rs.getDouble("Precio"));
                mascota.setImagen(rs.getBytes("imagen"));
                mascota.setTiempo(rs.getString("Tiempo"));
                mascota.setCalificacion(rs.getString("Calificacion"));  // Obtener la imagen en bytes

                // Establecer la categoría del producto
                mascota.setCategoria(rs.getString("CategoriaNombre"));
            }
        }

    } catch (SQLException e) {
        // Registrar o mostrar el error en caso de que ocurra
        e.printStackTrace();
        System.err.println("Error al obtener producto por ID: " + e.getMessage());
    }

    return mascota;
}

public List<Mascota> obtenerPorCategoria(String categoria) {
    List<Mascota> mascotas = new ArrayList<>();
    String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.DescripcionC, p.Precio, p.imagen, p.Tiempo, p.Calificacion, c.Descripcion AS CategoriaNombre FROM mascota p JOIN categoriam c ON p.CategoriaM = c.ID WHERE c.Descripcion LIKE ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        // Establecer el valor del parámetro de la consulta
        ps.setString(1,"%"+categoria+"%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Crear el objeto Producto
                Mascota mascota = new Mascota();
                mascota.setID(rs.getString("ID"));
                mascota.setNombre(rs.getString("Nombre"));
                mascota.setDescripcion(rs.getString("Descripcion"));
                mascota.setDescripcionC(rs.getString("DescripcionC"));
                mascota.setPrecio(rs.getDouble("Precio"));
                mascota.setImagen(rs.getBytes("imagen"));
                mascota.setTiempo(rs.getString("Tiempo"));
                mascota.setCalificacion(rs.getString("Calificacion"));  // Obtener la imagen en bytes

                // Establecer la categoría del producto
                mascota.setCategoria(rs.getString("CategoriaNombre"));
                
                // Agregar el producto a la lista
                mascotas.add(mascota);
            }
        }

    } catch (SQLException e) {
        // Registrar o mostrar el error en caso de que ocurra
        e.printStackTrace();
        System.err.println("Error al obtener producto por categoría: " + e.getMessage());
    }

    return mascotas;
}

    // Método para actualizar un proveedor
    public boolean actualizarMascota(Mascota mascota) {
        String query = """
        UPDATE mascota 
        SET Nombre = ?, 
            Descripcion = ?,
            DescripcionC = ?,
            Precio = ?,
            Tiempo= ?,
            Calificacion= ?,
            imagen= ?,     
            CategoriaM = ? 
        WHERE ID = ?
    """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getDescripcion());
            stmt.setString(3, mascota.getDescripcionC());
            stmt.setDouble(4, mascota.getPrecio());
            stmt.setString(5, mascota.getTiempo());
            stmt.setString(6, mascota.getCalificacion());
            stmt.setBytes(7, mascota.getImagen());
            stmt.setString(8, mascota.getCategoria());
            stmt.setString(9, mascota.getID());
            
            int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return false;
    }

    // Método para eliminar un proveedor por ID
    // Método para eliminar un producto por ID
public boolean eliminarMascota(String id) {
    String query = "DELETE FROM mascota WHERE ID = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {

        // Establecer el parámetro del ID
        stmt.setString(1, id);

        // Ejecutar la eliminación y devolver el resultado
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        // Manejo de la excepción
        e.printStackTrace();
        return false;
    }
}


    // Método para verificar si un proveedor existe
   // Método para verificar si un producto existe
public boolean doesMascotaExist(String id) {
    String query = "SELECT 1 FROM mascota WHERE ID = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {

        // Establecer el parámetro del ID
        stmt.setString(1, id);

        // Ejecutar la consulta y verificar si existe el producto
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next();  // Si hay un resultado, el producto existe
        }
    } catch (SQLException e) {
        // Manejo de la excepción
        e.printStackTrace();
        return false;
    }
}
public String obtenerUltimoIdProducto() {
    String ultimoId = null;
    String query = "SELECT ID FROM mascota ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

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