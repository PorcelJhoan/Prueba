package waliki.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import waliki.demo.bl.GestionPersonaBl;
import waliki.demo.dto.Persona;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController
@Service
public class PersonaController {



    @GetMapping(path= "/persona")
    public List<Persona> SeleccionarTodasPersonas() throws SQLException {
        return GestionPersonaBl.SeleccionarTodasPersonas();
    }

    @GetMapping(path= "/persona/{PersonId}")
    public Persona SeleccionarPersona(@PathVariable Integer PersonId) throws SQLException {
        if(PersonId <=0){

        }

        return GestionPersonaBl.SeleccionarPersona(PersonId);
    }

    @PostMapping(path= "/persona")
    public Persona insert_person(@RequestBody Persona persona) throws SQLException {
        if(persona.nombre == null || persona.nombre.equals("") ){

        }
        if(persona.apellido_casado == null || persona.apellido_casado.equals("") ){

        }
        if(persona.apellido_paterno == null || persona.apellido_paterno.equals("") ){

        }
        if(persona.apellido_materno == null || persona.apellido_materno.equals("") ){

        }
        if(persona.direccion_id == null || persona.direccion_id.equals("") ){

        }
        if(persona.telefono == null || persona.telefono.equals("") ){

        }
        return GestionPersonaBl.CrearPersona(persona);
    }

    @DeleteMapping(path= "/persona/{PersonId}")
    public Persona EliminarPersona(@PathVariable Integer PersonId) throws SQLException {
        if(PersonId <=0){

        }
        return GestionPersonaBl.EliminarPersona(PersonId);


    }
    @PutMapping(path= "/persona")
    public Persona ActualizarPersona(@RequestBody Persona ob) throws SQLException {
        return GestionPersonaBl.ActualizarPersona(ob);
    }
}
