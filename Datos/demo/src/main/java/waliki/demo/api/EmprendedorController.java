package waliki.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import waliki.demo.bl.GestionEmprendedorBl;
import waliki.demo.dto.Emprendedor;


import java.sql.SQLException;
import java.util.List;

@RestController
public class EmprendedorController {
    @Autowired
    private GestionEmprendedorBl gestionEmprendedorBl;

    @GetMapping(path= "/Emprendedor")
    public List<Emprendedor> SeleccionarTodasImagenes() throws SQLException {
        return gestionEmprendedorBl.SeleccionarEmprendedores();
    }

    @GetMapping(path= "/Emprendedor/{EmprendedorId}")
    public Emprendedor SeleccionarImagen(@PathVariable Integer EmprendedorId) throws SQLException {
        Emprendedor imagen = gestionEmprendedorBl.SeleccionarEmprendedor(EmprendedorId);
        if(imagen.emprendedor_id !=null){
            return gestionEmprendedorBl.SeleccionarEmprendedor(EmprendedorId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }
    }

    @PostMapping(path= "/Emprendedor")
    public Emprendedor CrearEmprendedor(@RequestBody Emprendedor imagen) throws SQLException {
        Emprendedor imagen2 = gestionEmprendedorBl.CrearEmprendedor(imagen);
        if(imagen2.emprendedor_id !=null){
            return gestionEmprendedorBl.CrearEmprendedor(imagen);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe Emprendedor con codigo");
        }

    }

    @DeleteMapping(path= "/Emprendedor/{EmprendedorId}")
    public Emprendedor EliminarEmprendedor(@PathVariable Integer EmprendedorId) throws SQLException {
        Emprendedor imagen = gestionEmprendedorBl.EliminarEmprendedor(EmprendedorId);

        if(imagen.emprendedor_id !=null){
            return gestionEmprendedorBl.EliminarEmprendedor(EmprendedorId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }



    }
    @PutMapping(path= "/Emprendedor")
    public Emprendedor ActualizarEmprendedor(@RequestBody Emprendedor ob) throws SQLException {
        return gestionEmprendedorBl.ActualizarEmprendedor(ob);
    }



}
