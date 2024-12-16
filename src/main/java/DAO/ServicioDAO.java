/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import Logica.Servicios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dnayj
 */
public class ServicioDAO {
  public boolean agregarServicio(Servicios servicio) {
        String query = "INSERT INTO servicio (ID, Nombre, Descripcion, DescripcionC, Precio,imagen, Tiempo, Calificacion, CategoriaS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, servicio.getID());
            stmt.setString(2, servicio.getNombre());
            stmt.setString(3, servicio.getDescripcion());
            stmt.setString(4, servicio.getDescripcionC());
            stmt.setDouble(5, servicio.getPrecio());
            stmt.setBytes(6, servicio.getImagen());
            stmt.setString(7, servicio.getTiempo());
            stmt.setString(8, servicio.getCalificacion());
            stmt.setString(9, servicio.getCategoria());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
          
        }  
        return false;
    }

    // Método para obtener todos los proveedores
    public List<Servicios> obtenerTodos() {
        List<Servicios> servicioList = new ArrayList<>();
        String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.DescripcionC, p.Precio, p.imagen, p.Tiempo, p.Calificacion, c.Descripcion AS CategoriaNombre FROM servicio p JOIN categorias c ON p.CategoriaS = c.ID";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Servicios servicio = new Servicios();
                servicio.setID(rs.getString("ID"));
                servicio.setNombre(rs.getString("Nombre"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setDescripcionC(rs.getString("DescripcionC"));
                servicio.setPrecio(rs.getDouble("Precio"));
                servicio.setImagen(rs.getBytes("imagen"));
                servicio.setTiempo(rs.getString("Tiempo"));
                servicio.setCalificacion(rs.getString("Calificacion"));
                servicio.setCategoria(rs.getString("CategoriaNombre")); 
                servicioList.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener todos los ingredientes: " + e.getMessage());
        }

        return servicioList;
    }

    // Método para obtener un proveedor por ID
    public Servicios obtenerPorId(String id) {
    Servicios servicio = null;
    String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.DescripcionC, p.Precio, p.imagen, p.Tiempo, p.Calificacion, c.Descripcion AS CategoriaNombre FROM servicio p JOIN categorias c ON p.CategoriaS = c.ID WHERE p.ID = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        // Establecer el valor del parámetro de la consulta
        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Crear el objeto Producto
                servicio = new Servicios();
                servicio.setID(rs.getString("ID"));
                servicio.setNombre(rs.getString("Nombre"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setDescripcionC(rs.getString("DescripcionC"));
                servicio.setPrecio(rs.getDouble("Precio"));
                servicio.setImagen(rs.getBytes("imagen"));
                servicio.setTiempo(rs.getString("Tiempo"));
                servicio.setCalificacion(rs.getString("Calificacion"));  // Obtener la imagen en bytes

                // Establecer la categoría del producto
                servicio.setCategoria(rs.getString("CategoriaNombre"));
            }
        }

    } catch (SQLException e) {
        // Registrar o mostrar el error en caso de que ocurra
        e.printStackTrace();
        System.err.println("Error al obtener producto por ID: " + e.getMessage());
    }

    return servicio;
}

public List<Servicios> obtenerPorCategoria(String categoria) {
    List<Servicios> servicios = new ArrayList<>();
    String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.DescripcionC, p.Precio, p.imagen, p.Tiempo, p.Calificacion, c.Descripcion AS CategoriaNombre FROM servicio p JOIN categorias c ON p.CategoriaS = c.ID WHERE c.Descripcion = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        // Establecer el valor del parámetro de la consulta
        ps.setString(1, categoria);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Crear el objeto Producto
                Servicios servicio = new Servicios();
                servicio.setID(rs.getString("ID"));
                servicio.setNombre(rs.getString("Nombre"));
                servicio.setDescripcion(rs.getString("Descripcion"));
                servicio.setDescripcionC(rs.getString("DescripcionC"));
                servicio.setPrecio(rs.getDouble("Precio"));
                servicio.setImagen(rs.getBytes("imagen"));
                servicio.setTiempo(rs.getString("Tiempo"));
                servicio.setCalificacion(rs.getString("Calificacion"));  // Obtener la imagen en bytes

                // Establecer la categoría del producto
                servicio.setCategoria(rs.getString("CategoriaNombre"));
                
                // Agregar el producto a la lista
                servicios.add(servicio);
            }
        }

    } catch (SQLException e) {
        // Registrar o mostrar el error en caso de que ocurra
        e.printStackTrace();
        System.err.println("Error al obtener producto por categoría: " + e.getMessage());
    }

    return servicios;
}

    // Método para actualizar un proveedor
    public boolean actualizarServicio(Servicios servicio) {
        String query = """
        UPDATE servicio 
        SET Nombre = ?, 
            Descripcion = ?,
            DescripcionC = ?,
            Precio = ?,
            imagen = ?,
            Tiempo= ?,
            Calificacion= ?,         
            CategoriaS = ? 
                       
        WHERE ID = ?
    """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, servicio.getNombre());
            stmt.setString(2, servicio.getDescripcion());
            stmt.setString(3, servicio.getDescripcionC());
            stmt.setDouble(4, servicio.getPrecio());
            stmt.setBytes(5, servicio.getImagen());
            stmt.setString(6, servicio.getTiempo());
            stmt.setString(7, servicio.getCalificacion());
            stmt.setString(8, servicio.getCategoria());
            stmt.setString(9, servicio.getID());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para eliminar un proveedor por ID
    // Método para eliminar un producto por ID
public boolean eliminarServicio(String id) {
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
public boolean doesServicioExist(String id) {
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
    String query = "SELECT ID FROM servicio ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

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
