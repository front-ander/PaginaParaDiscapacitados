    package DAO;

    import Logica.Usuario;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException; 
    import java.util.ArrayList;
    import java.util.Base64;
    import java.util.List;
    import java.util.logging.Level;
    import java.util.logging.Logger;


    public class UsuarioDAO {

        // Logger para manejar las excepciones
        private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());


        // Método para obtener usuarios por su ID
        public List<Usuario> obtenerUsuariosPorId(String id) {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                       "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.ID = ?";

        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getString("ID"));
                    usuario.setDni(rs.getString("DNI"));
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setCara(rs.getBytes("Cara"));
                    usuario.setCategoria(rs.getString("CategoriaNombre"));

                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener usuarios por ID", e);
        }

        return usuarios; // Retornar lista vacía si no se encontraron resultados
    }

        public List<Usuario> obtenerUsuariosPorDni(String DNI) {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.DNI LIKE ?";

        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1,"%"+DNI+"%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getString("ID"));
                    usuario.setDni(rs.getString("DNI"));
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setCara(rs.getBytes("Cara")); // Obtén la imagen como byte[]
                    usuario.setCategoria(rs.getString("CategoriaNombre"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener usuarios por DNI", e);
        }

        return usuarios;
    }
        public Usuario obtenerUsuarioPorDni(String dni) {
        String query = "SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE DNI = ?";
        Usuario usuario = null;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getString("ID"));
                    usuario.setDni(rs.getString("DNI"));
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setCara(rs.getBytes("Cara"));

                    // Ahora accede correctamente al alias CategoriaNombre
                    usuario.setCategoria(rs.getString("CategoriaNombre")); 



                    // Obtener la imagen de la cara y convertirla a Base64
                    byte[] caraBytes = rs.getBytes("Cara");
                    if (caraBytes != null) {
                        String caraBase64 = Base64.getEncoder().encodeToString(caraBytes);
                        usuario.setCaraBase64(caraBase64);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener usuario por DNI: " + e.getMessage());
        }

        return usuario;
    }


    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre FROM usuario u JOIN categoria c ON u.Categoria = c.ID";

        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getString("ID"));
                    usuario.setDni(rs.getString("DNI"));
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setCara(rs.getBytes("Cara"));

                    // Ahora accede correctamente al alias CategoriaNombre
                    usuario.setCategoria(rs.getString("CategoriaNombre")); 

                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener usuarios", e);
        }

        return usuarios;
    }

    /*public List<Usuario> obtenerUsuariosIn() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT DNI, Cara FROM usuario";

        try (Connection con = Conexion.getConnection(); 
             PreparedStatement ps = con.prepareStatement(query)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setDni(rs.getString("DNI"));
                    usuario.setCara(rs.getBytes("Cara"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener usuarios", e);
        }

        return usuarios;
    }*/
        // Método para registrar un nuevo usuario
        public boolean registrarUsuario(Usuario usuario) throws Exception {
            // Validaciones básicas de campos no nulos
            if (usuario == null || usuario.getId() == null || usuario.getDni() == null ||
                usuario.getCorreo() == null || usuario.getCara() == null || usuario.getCategoria()== null) {
                logger.log(Level.WARNING, "Datos de usuario inválidos para registrar");
                throw new Exception("Datos de usuario inválidos."); // Lanza una excepción en caso de datos inválidos
            }

            String query = "INSERT INTO usuario (ID, DNI, Correo, Cara, Categoria) VALUES (?, ?, ?, ?, ?)";

            try (Connection con = Conexion.getConnection(); 
                 PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, usuario.getId());
                ps.setString(2, usuario.getDni());
                ps.setString(3, usuario.getCorreo());
                ps.setBytes(4, usuario.getCara()); // Asegúrate de que `Cara` se almacene como cadena (base64)
                ps.setString(5, usuario.getCategoria());

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    logger.log(Level.INFO, "Usuario registrado exitosamente");
                    return true; // Devuelve true si se registró exitosamente
                } else {
                    logger.log(Level.WARNING, "No se pudo registrar el usuario, filas afectadas: " + rowsInserted);
                    throw new Exception("No se pudo registrar el usuario.");
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error en la consulta SQL al registrar usuario: " + e.getMessage(), e);
                throw new Exception("Error en la consulta SQL: " + e.getMessage());
            }
        }

        public String obtenerUltimoIdUsuario() {
        String ultimoId = null;
        String query = "SELECT ID FROM usuario ORDER BY CAST(SUBSTRING(ID, 4) AS UNSIGNED) DESC LIMIT 1";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                ultimoId = rs.getString("ID");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener el último ID de usuario", e);
        }
        return ultimoId;
    }

         public boolean existeDniOCorreo(String dni, String correo) {
        String query = "SELECT COUNT(*) FROM usuario WHERE DNI = ? OR Correo = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, dni);
            ps.setString(2, correo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Devuelve true si ya existe un registro con el mismo DNI o correo
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar DNI o correo", e);
        }
        return false;
    }
             public Usuario obtenerUsuarioPorDNI(String dni) {
        String query = "SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre "  +
                     "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.DNI = ?";
        Usuario usuario = null;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setDni(rs.getString("DNI"));
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setId(rs.getString("ID"));
                    usuario.setCategoria(rs.getString("CategoriaNombre")); // Asignar nombre de la categoría correctamente

                    // Obtener la imagen de la cara y convertirla a Base64
                    byte[] caraBytes = rs.getBytes("Cara");
                    if (caraBytes != null) {
                        String caraBase64 = Base64.getEncoder().encodeToString(caraBytes);
                        usuario.setCaraBase64(caraBase64);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener usuario por DNI: " + e.getMessage());
        }

        return usuario;
    }
         public Usuario obtenerUsuarioPorDNITRAJ(String dni) {
        String query = "SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " +
                       "FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.DNI = ?";
        Usuario usuario = null;

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setDni(rs.getString("DNI"));
                    usuario.setCorreo(rs.getString("Correo"));
                    usuario.setId(rs.getString("ID"));
                    usuario.setCategoria(rs.getString("CategoriaNombre")); // Asignar nombre de la categoría correctamente

                    // Obtener la imagen de la cara y convertirla a Base64
                    byte[] caraBytes = rs.getBytes("Cara");
                    if (caraBytes != null) {
                        String caraBase64 = Base64.getEncoder().encodeToString(caraBytes);
                        usuario.setCaraBase64(caraBase64);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener usuario por DNI: " + e.getMessage());
        }

        return usuario;
    }

         public boolean eliminarUsuario(String id) {
        String query = "DELETE FROM usuario WHERE ID = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar usuario", e);
        }
        return false;
    }
         //"SELECT u.ID, u.DNI, u.Correo, u.Cara, c.Descripcion AS CategoriaNombre " + FROM usuario u JOIN categoria c ON u.Categoria = c.ID WHERE u.DNI = ?"
    public boolean actualizarUsuario(Usuario usuario) {
        String query = """
            UPDATE usuario 
            SET DNI = ?, 
                Correo = ?, 
                Categoria = ? 
            WHERE ID = ?
        """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getCategoria()); // Usa la descripción aquí (e.g., Admin)
            ps.setString(4, usuario.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
        }
        return false;
    }
    public boolean bajaUsuario(Usuario usuario) {
        String query = """
            UPDATE usuario 
            SET DNI = ?, 
                Correo = ?, 
                Categoria = ? 
            WHERE ID = ?
        """;
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, usuario.getDni());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, "CAT4"); // Usa la descripción aquí (e.g., Admin)
            ps.setString(4, usuario.getId());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
        }
        return false;
    }
    public boolean existeDni(String dni) {
        String query = "SELECT COUNT(*) FROM usuario WHERE DNI = ? ";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Devuelve true si ya existe un registro con el mismo DNI o correo
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar DNI o correo", e);
        }
        return false;
    }
    }
