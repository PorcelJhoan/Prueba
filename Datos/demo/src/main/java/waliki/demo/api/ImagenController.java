package waliki.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import waliki.demo.bl.GestionImagenBl;
import waliki.demo.bl.GestionUsuarioBl;
import waliki.demo.dto.Imagen;


import java.sql.SQLException;
import java.util.List;

@Service
public class ImagenController {
    @Autowired
    private GestionImagenBl gestionImagenBl;

    @GetMapping(path= "/imagen")
    public List<Imagen> SeleccionarTodasImagenes() throws SQLException {
        return gestionImagenBl.SeleccionarTodasImagenes();
    }

    @GetMapping(path= "/imagen/{imagenId}")
    public Imagen SeleccionarImagen(@PathVariable Integer imagenId) throws SQLException {
        Imagen imagen = gestionImagenBl.SeleccionarImagen(imagenId);
        if(imagen.imagen_id !=null){
            return gestionImagenBl.SeleccionarImagen(imagenId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }
    }

    @PostMapping(path= "/imagen")
    public Imagen CrearImagen(@RequestBody Imagen imagen) throws SQLException {
        Imagen imagen2 = gestionImagenBl.CrearImagen(imagen);
        if(imagen2.imagen_id !=null){
            return gestionImagenBl.CrearImagen(imagen);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }

    }

    @DeleteMapping(path= "/imagen/{imagenId}")
    public Imagen EliminarImagen(@PathVariable Integer imagenId) throws SQLException {
        Imagen imagen = gestionImagenBl.EliminarImagen(imagenId);

        if(imagen.imagen_id !=null){
            return gestionImagenBl.EliminarImagen(imagenId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }



    }
    @PutMapping(path= "/imagen")
    public Imagen ActualizarImagen(@RequestBody Imagen ob) throws SQLException {
        return gestionImagenBl.ActualizarImagen(ob);
    }
}
