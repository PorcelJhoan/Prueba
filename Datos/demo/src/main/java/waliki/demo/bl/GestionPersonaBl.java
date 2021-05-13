package waliki.demo.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waliki.demo.dao.PersonaDao;
import waliki.demo.dto.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GestionPersonaBl {

    @Autowired
    PersonaDao personaDao;

    public Persona CrearPersona(Persona persona)throws SQLException{
        return personaDao.CrearPersona(persona);
    }
    public List<Persona> SeleccionarTodasPersonas() throws SQLException {
        return personaDao.SeleccionarTodasPersonas();
    }
    public Persona SeleccionarPersona(Integer PersonId) throws SQLException {

        return personaDao.SeleccionarPersona(PersonId);
    }
    public Persona EliminarPersona(Integer PersonId) throws SQLException {

        return personaDao.EliminarPersona(PersonId);
    }

    public Persona ActualizarPersona(Persona ob) throws SQLException {

        return personaDao.ActualizarPersona(ob);
    }
}
