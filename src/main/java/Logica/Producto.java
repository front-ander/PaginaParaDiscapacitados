/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.Base64;

/**
 *
 * @author dnayj
 */
public class Producto {
    private String ID;
    private String Nombre;
    private String Descripcion;
    private Double Precio;
    private int Stock;
    private byte[] imagen;
    private String Categoria;

    public Producto() {
    }

    public Producto(String ID, String Nombre, String Descripcion, Double Precio, int Stock, byte[] imagen, String Categoria) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Stock = Stock;
        this.imagen = imagen;
        this.Categoria = Categoria;
    }

   

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    public String getImagenBase64() {
    if (imagen != null) {
        return Base64.getEncoder().encodeToString(imagen);
    } else {
        return null; // O puedes devolver una cadena vacía, dependiendo de cómo quieras manejar este caso
    }
}

    public void setImagenBase64(String imagenBase64) {
        if (imagenBase64 != null && !imagenBase64.isEmpty()) {
            this.imagen = Base64.getDecoder().decode(imagenBase64);
        } else {
            this.imagen = null; // Opcional: manejar el caso en que la cadena Base64 sea nula o vacía
        }
    }
    
}
