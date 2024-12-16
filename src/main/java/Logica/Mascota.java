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
public class Mascota {
    private String ID;
    private String Nombre;
    private String Descripcion;
    private Double Precio;
    private byte[] imagen;
    private String Categoria;
    private String tiempo;
    private String Calificacion;
    private String DescripcionC;

    public Mascota() {
    }

    public Mascota(String ID, String Nombre, String Descripcion, Double Precio, byte[] imagen, String Categoria, String tiempo, String Calificacion, String DescripcionC) {
        this.ID = ID;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.imagen = imagen;
        this.Categoria = Categoria;
        this.tiempo = tiempo;
        this.Calificacion = Calificacion;
        this.DescripcionC = DescripcionC;
    }

    

    

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
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

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String Calificacion) {
        this.Calificacion = Calificacion;
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

    public String getDescripcionC() {
        return DescripcionC;
    }

    public void setDescripcionC(String DescripcionC) {
        this.DescripcionC = DescripcionC;
    }
}
