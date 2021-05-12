package waliki.demo.bl;

import org.springframework.stereotype.Service;
import waliki.demo.dao.PersonaDao;
import waliki.demo.dto.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestionPersonaBl {
    public static Persona CrearPersona(Persona persona){
        return PersonaDao.CrearPersona(persona);
    }
    public static List<Persona> SeleccionarTodasPersonas() throws SQLException {
        return PersonaDao.SeleccionarTodasPersonas();
    }
    public static Persona SeleccionarPersona(Integer PersonId) throws SQLException {

        return PersonaDao.SeleccionarPersona(PersonId);
    }
    public static Persona EliminarPersona(Integer PersonId) throws SQLException {

        return PersonaDao.EliminarPersona(PersonId);
    }

    public static Persona ActualizarPersona(Persona ob) throws SQLException {

        return PersonaDao.ActualizarPersona(ob);
    }
}
