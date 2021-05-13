package waliki.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import waliki.demo.bl.GestionUsuarioBl;
import waliki.demo.dto.Usuario;

import java.sql.SQLException;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private GestionUsuarioBl gestionUsuarioBl;

    @GetMapping(path= "/usuario")
    public List<Usuario> SeleccionarTodosUsuarios() throws SQLException {
        return gestionUsuarioBl.SeleccionarTodosUsuarios();
    }

    @GetMapping(path= "/usuario/{usuarioId}")
    public Usuario SeleccionarUsuario(@PathVariable Integer usuarioId) throws SQLException {
        Usuario usuario = gestionUsuarioBl.SeleccionarUsuario(usuarioId);
        if(usuario.usuario_id !=null){
            return gestionUsuarioBl.SeleccionarUsuario(usuarioId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }
    }

    @PostMapping(path= "/usuario")
    public Usuario CrearUsuario(@RequestBody Usuario usuario) throws SQLException {
        Usuario usuario2 = gestionUsuarioBl.CrearUsuario(usuario);
        if(usuario2.persona_id !=null){
            return gestionUsuarioBl.CrearUsuario(usuario);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }

    }

    @DeleteMapping(path= "/usuario/{usuarioId}")
    public Usuario EliminarUsuario(@PathVariable Integer usuarioId) throws SQLException {
        Usuario usuario = gestionUsuarioBl.EliminarUsuario(usuarioId);

        if(usuario.usuario_id !=null){
            return gestionUsuarioBl.EliminarUsuario(usuarioId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }



    }
    @PutMapping(path= "/usuario")
    public Usuario ActualizarUsuario(@RequestBody Usuario ob) throws SQLException {
        return gestionUsuarioBl.ActualizarUsuario(ob);
    }

}
