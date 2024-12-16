/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author dnayj
 */
public class DetalleVenta1 {
    private String idDetalleVenta;  // ID del detalle de la venta
    private String idVenta;         // ID de la venta a la que pertenece
    private String ventas;
    private int cantidad;           // Cantidad de artículos
    private double precioUnitario;  // Precio unitario del artículo
    private double subtotal;        // Subtotal (precioUnitario * cantidad)
    private String estado;          // Estado de la venta (enviado, pendiente, cancelado, entregado)

    public DetalleVenta1() {
    }

    public DetalleVenta1(String idDetalleVenta, String idVenta, String ventas, int cantidad, double precioUnitario, double subtotal, String estado) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.ventas = ventas;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.estado = estado;
    }

    
   

    // Getters y Setters
    public String getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(String idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

  
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVentas() {
        return ventas;
    }

    public void setVentas(String ventas) {
        this.ventas = ventas;
    }
    
}
