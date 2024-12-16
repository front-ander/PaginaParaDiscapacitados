/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

/**
 *
 * @author dnayj
 */
public class DetalleCarrito {
    private String Id;
    private String Ventas;
    private int Cantidad;
    private String IDCARR;
    private Double PrecioUnitario;
    private Double Subtotal;
    private int indice;
    String Tipo;
    public DetalleCarrito() {
    }

    public DetalleCarrito(String Id, String Ventas, int Cantidad, String IDCARR, Double PrecioUnitario, Double Subtotal, int indice, String Tipo) {
        this.Id = Id;
        this.Ventas = Ventas;
        this.Cantidad = Cantidad;
        this.IDCARR = IDCARR;
        this.PrecioUnitario = PrecioUnitario;
        this.Subtotal = Subtotal;
        this.indice = indice;
        this.Tipo = Tipo;
    }

   

    

    

    public Double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getVentas() {
        return Ventas;
    }

    public void setVentas(String Ventas) {
        this.Ventas = Ventas;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getIDCARR() {
        return IDCARR;
    }

    public void setIDCARR(String IDCARR) {
        this.IDCARR = IDCARR;
    }

    public Double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(Double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
        public String getTipo() {
    if (this.getVentas() != null) {
        if (this.getVentas().startsWith("MAC")) {
            return "Mascota";
        } else if (this.getVentas().startsWith("PRO")) {
            return "Producto";
        } else if (this.getVentas().startsWith("SER")) {
            return "Servicio";
        }
    }
    return "Desconocido";
}    
         public void seTipo(String Tipo) {
        this.Tipo = Tipo;
    }   
}
