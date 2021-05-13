package waliki.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import waliki.demo.bl.GestionPersonaBl;
import waliki.demo.dto.Persona;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController

public class PersonaController {

    @Autowired
    private GestionPersonaBl gestionPersonaBl;


    @GetMapping("/home")
    public String Home(Model model){
        return "index";
    }


    @GetMapping(path= "/persona")
    public List<Persona> SeleccionarTodasPersonas() throws SQLException {
        return gestionPersonaBl.SeleccionarTodasPersonas();
    }

    @GetMapping(path= "/persona/{PersonId}")
    public Persona SeleccionarPersona(@PathVariable Integer PersonId) throws SQLException {
        Persona persona = gestionPersonaBl.EliminarPersona(PersonId);
        if(persona.persona_id !=null){
            return gestionPersonaBl.EliminarPersona(PersonId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo"+PersonId);
        }
    }

    @PostMapping(path= "/persona")
    public Persona insert_person(@RequestBody Persona persona) throws SQLException {
        Persona persona2 = gestionPersonaBl.CrearPersona(persona);
        if(persona2.persona_id !=null){
            return gestionPersonaBl.CrearPersona(persona);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo");
        }

    }

    @DeleteMapping(path= "/persona/{PersonId}")
    public Persona EliminarPersona(@PathVariable Integer PersonId) throws SQLException {
        Persona persona = gestionPersonaBl.EliminarPersona(PersonId);

        if(persona.persona_id !=null){
            return gestionPersonaBl.EliminarPersona(PersonId);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No existe la persona con codigo"+PersonId);
        }



    }
    @PutMapping(path= "/persona")
    public Persona ActualizarPersona(@RequestBody Persona ob) throws SQLException {
        return gestionPersonaBl.ActualizarPersona(ob);
    }
}
