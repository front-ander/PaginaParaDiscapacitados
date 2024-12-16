/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Logica.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {

   public boolean agregarProducto(Producto producto) {
        String query = "INSERT INTO producto (ID, Nombre, Descripcion, Precio, Stock, imagen, CategoriaP) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection(); 
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, producto.getID());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setDouble(4, producto.getPrecio());
            stmt.setInt(5, producto.getStock());
            stmt.setBytes(6, producto.getImagen());
            stmt.setString(7, producto.getCategoria());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
          
        }  
        return false;
    }

    // Método para obtener todos los proveedores
    public List<Producto> obtenerTodos() {
        List<Producto> productoList = new ArrayList<>();
        String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.Precio, p.Stock, p.imagen, c.Descripcion AS CategoriaNombre FROM producto p JOIN categoriap c ON p.CategoriaP = c.ID";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setID(rs.getString("ID"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setStock(rs.getInt("Stock"));
                producto.setImagen(rs.getBytes("imagen"));
                
                // Ahora accede correctamente al alias CategoriaNombre
                producto.setCategoria(rs.getString("CategoriaNombre")); 
                productoList.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener todos los ingredientes: " + e.getMessage());
        }

        return productoList;
    }

    // Método para obtener un proveedor por ID
    public Producto obtenerPorId(String id) {
    Producto producto = null;
    String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.Precio, p.Stock, p.imagen, c.Descripcion AS CategoriaNombre " +
                   "FROM producto p JOIN categoriap c ON p.CategoriaP = c.ID WHERE p.ID = ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        // Establecer el valor del parámetro de la consulta
        ps.setString(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Crear el objeto Producto
                producto = new Producto();
                producto.setID(rs.getString("ID"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setStock(rs.getInt("Stock"));
                producto.setImagen(rs.getBytes("imagen"));  // Obtener la imagen en bytes

                // Establecer la categoría del producto
                producto.setCategoria(rs.getString("CategoriaNombre"));
            }
        }

    } catch (SQLException e) {
        // Registrar o mostrar el error en caso de que ocurra
        e.printStackTrace();
        System.err.println("Error al obtener producto por ID: " + e.getMessage());
    }

    return producto;
}

public List<Producto> obtenerPorCategoria(String categoria) {
    List<Producto> productos = new ArrayList<>();
    String query = "SELECT p.ID, p.Nombre, p.Descripcion, p.Precio, p.Stock, p.imagen, c.Descripcion AS CategoriaNombre " +
                   "FROM producto p JOIN categoriap c ON p.CategoriaP = c.ID WHERE c.Descripcion LIKE ?";

    try (Connection con = Conexion.getConnection();
         PreparedStatement ps = con.prepareStatement(query)) {

        // Establecer el valor del parámetro de la consulta
        ps.setString(1,"%"+categoria+"%");

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Crear el objeto Producto
                Producto producto = new Producto();
                producto.setID(rs.getString("ID"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setPrecio(rs.getDouble("Precio"));
                producto.setStock(rs.getInt("Stock"));
                producto.setImagen(rs.getBytes("imagen"));  // Obtener la imagen en bytes

                // Establecer la categoría del producto
                producto.setCategoria(rs.getString("CategoriaNombre"));
                
                // Agregar el producto a la lista
                productos.add(producto);
            }
        }

    } catch (SQLException e) {
        // Registrar o mostrar el error en caso de que ocurra
        e.printStackTrace();
        System.err.println("Error al obtener producto por categoría: " + e.getMessage());
    }

    return productos;
}

    // Método para actualizar un proveedor
    public boolean actualizarProducto(Producto producto) {
        String query = """
        UPDATE producto 
        SET Nombre = ?, 
            Descripcion = ?,
            Precio = ?,
            Stock = ?,    
            imagen =?,    
            CategoriaP = ? 
        WHERE ID = ?
    """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setBytes(5, producto.getImagen());
            stmt.setString(6, producto.getCategoria());
            stmt.setString(7, producto.getID());
            int filasAfectadas = stmt.executeUpdate();
        return filasAfectadas > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return false;
    }

    // Método para eliminar un proveedor por ID
    // Método para eliminar un producto por ID
public boolean eliminarProducto(String id) {
    String query = "DELETE FROM producto WHERE ID = ?";

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
public boolean doesProductoExist(String id) {
    String query = "SELECT 1 FROM producto WHERE ID = ?";

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
    String query = "SELECT ID FROM producto ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

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