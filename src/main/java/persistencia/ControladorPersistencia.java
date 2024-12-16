/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Logica.Usuario;
import java.util.List;

public class ControladorPersistencia {
    UsuarioJpaController usuJpa = new UsuarioJpaController ();
    
    public void crearUsuario (Usuario usu){
        
        usuJpa.create(usu);
        
    }
    public List<Usuario> traerUsuarios (){
        return usuJpa.findUsuarioEntities();
    }
}
