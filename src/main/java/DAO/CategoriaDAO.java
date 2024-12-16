/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Logica.Categoria;
public class CategoriaDAO {
    public List<Categoria> obtenerCategoriasP() {
    List<Categoria> categorias = new ArrayList<>();
    String query = "SELECT ID, Descripcion FROM categoriap";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setID(rs.getString("ID")); // Ajusta el tipo según la columna ID en tu BD
            categoria.setDescripcion(rs.getString("Descripcion"));
            categorias.add(categoria);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return categorias;
}
    public List<Categoria> obtenerCategoriasS() {
    List<Categoria> categorias = new ArrayList<>();
    String query = "SELECT ID, Descripcion FROM categorias";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setID(rs.getString("ID")); // Ajusta el tipo según la columna ID en tu BD
            categoria.setDescripcion(rs.getString("Descripcion"));
            categorias.add(categoria);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return categorias;
}
    public List<Categoria> obtenerCategoriasM() {
    List<Categoria> categorias = new ArrayList<>();
    String query = "SELECT ID, Descripcion FROM categoriam";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setID(rs.getString("ID")); // Ajusta el tipo según la columna ID en tu BD
            categoria.setDescripcion(rs.getString("Descripcion"));
            categorias.add(categoria);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return categorias;
}
public List<Categoria> obtenerCategoriasU() {
    List<Categoria> categorias = new ArrayList<>();
    String query = "SELECT ID, Descripcion FROM categoria";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setID(rs.getString("ID")); // Ajusta el tipo según la columna ID en tu BD
            categoria.setDescripcion(rs.getString("Descripcion"));
            categorias.add(categoria);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return categorias;
}
public List<Categoria> obtenerEstado() {
    List<Categoria> categorias = new ArrayList<>();
    String query = "SELECT ID, Descripcion FROM estado";

    try (Connection con = Conexion.getConnection();
         PreparedStatement stmt = con.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setID(rs.getString("ID")); // Ajusta el tipo según la columna ID en tu BD
            categoria.setDescripcion(rs.getString("Descripcion"));
            categorias.add(categoria);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return categorias;
}
}
