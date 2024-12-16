/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.List;

/**
 *
 * @author dnayj
 */
public class Carrito {
    private String Id;
    private String Idusu;
    private List<DetalleCarrito> detalles;
    public Carrito() {
    }

    public Carrito(String Id, String Idusu, List<DetalleCarrito> detalles) {
        this.Id = Id;
        this.Idusu = Idusu;
        this.detalles = detalles;
    }

    

   

    public String getIdusu() {
        return Idusu;
    }

    public void setIdusu(String Idusu) {
        this.Idusu = Idusu;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public List<DetalleCarrito> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarrito> detalles) {
        this.detalles = detalles;
    }

    
    
}
