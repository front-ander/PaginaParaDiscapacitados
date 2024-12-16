package Logica;

import java.util.Base64;

 public class Usuario{
    
    private String id;
    private String dni;
    private String correo;
    private byte[] cara;
    private String categoria;
    
    
    public Usuario(){
        
    }

    public Usuario(String id, String dni, String correo, byte[] cara, String categoria) {
        this.id = id;
        this.dni = dni;
        this.correo = correo;
        this.cara = cara;
        this.categoria = categoria;
    }

    

    public byte[] getCara() {
        return cara;
    }

    public void setCara(byte[] cara) {
        this.cara = cara;
    }
    public String getCaraBase64() {
    if (cara != null) {
        return Base64.getEncoder().encodeToString(cara);
    } else {
        return null; // O puedes devolver una cadena vacía, dependiendo de cómo quieras manejar este caso
    }
}

    public void setCaraBase64(String caraBase64) {
        if (caraBase64 != null && !caraBase64.isEmpty()) {
            this.cara = Base64.getDecoder().decode(caraBase64);
        } else {
            this.cara = null; // Opcional: manejar el caso en que la cadena Base64 sea nula o vacía
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
}
