package waliki.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import waliki.demo.bl.GestionTipo_IdentificacionBl;
import waliki.demo.dto.Tipo_identificacion;

import java.sql.SQLException;
import java.util.List;

@RestController
public class Tipo_identificacionController {
    @Autowired
    private GestionTipo_IdentificacionBl gestionTipo_identificacionBl;

    @GetMapping(path= "/Tipo_identificacion")
    public List<Tipo_identificacion> SeleccionarTodasImagenes() throws SQLException {
        return gestionTipo_identificacionBl.SeleccionarTipoIdentificaciones();
    }

    @GetMapping(path= "/Tipo_identificacion/{Tipo_identificacionId}")
    public Tipo_identificacion SeleccionarImagen(@PathVariable Integer Tipo_identificacionId) throws SQLException {
        Tipo_identificacion tipo_identificacion = gestionTipo_identificacionBl.SeleccionarTipoIdentificacion(Tipo_identificacionId);
        if(tipo_identificacion.tipo_identificacion_id !=null){
            return gestionTipo_identificacionBl.SeleccionarTipoIdentificacion(Tipo_identificacionId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }
    }

    @PostMapping(path= "/Tipo_identificacion")
    public Tipo_identificacion CrearImagen(@RequestBody Tipo_identificacion imagen) throws SQLException {
        Tipo_identificacion imagen2 = gestionTipo_identificacionBl.CrearTipoIdentificacion(imagen);
        if(imagen2.tipo_identificacion_id !=null){
            return gestionTipo_identificacionBl.CrearTipoIdentificacion(imagen);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }

    }

    @DeleteMapping(path= "/Tipo_identificacion/{Tipo_identificacionId}")
    public Tipo_identificacion EliminarImagen(@PathVariable Integer imagenId) throws SQLException {
        Tipo_identificacion imagen = gestionTipo_identificacionBl.EliminarTipoIdentificacion(imagenId);

        if(imagen.tipo_identificacion_id !=null){
            return gestionTipo_identificacionBl.EliminarTipoIdentificacion(imagenId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }



    }
    @PutMapping(path= "/Tipo_identificacion")
    public Tipo_identificacion ActualizarImagen(@RequestBody Tipo_identificacion ob) throws SQLException {
        return gestionTipo_identificacionBl.ActualizarTipoIdentificacion(ob);
    }

}
