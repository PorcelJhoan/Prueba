package waliki.demo.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import waliki.demo.bl.GestionPersonaBl;
import waliki.demo.dto.Persona;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
@Service
public class PersonaDao {


    @Autowired
    public static DataSource dataSource;

    public static Persona CrearPersona(Persona ob){
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            PreparedStatement preesta;
            preesta = con.prepareStatement("INSERT INTO persona(persona_id, nombre, apellido_paterno, apellido_materno, apellido_casado, telefono, fecha_nacimiento, direccion_id, correo_electronico,tipo_identificacion_id,numero_identificacion)" + " VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            preesta.setInt(1, ob.persona_id);
            preesta.setString(2, ob.nombre);
            preesta.setString(3, ob.apellido_paterno);
            preesta.setString(4, ob.apellido_materno);
            preesta.setString(5, ob.apellido_casado);
            preesta.setString(6, ob.telefono);
            preesta.setString(7, ob.fecha_nacimiento);
            preesta.setInt(8, ob.direccion_id);
            preesta.setString(9, ob.correo_electronico);
            preesta.setInt(10, ob.tipo_identificacion_id);
            preesta.setString(11, ob.numero_identificacion);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }

    public static List<Persona> SeleccionarTodasPersonas() throws SQLException {
        List<Persona> array=new ArrayList<>();

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select persona_id, nombre, apellido_paterno, apellido_materno, apellido_casado, telefono, fecha_nacimiento, direccion_id, correo_electronico,tipo_identificacion_id,numero_identificacion from persona ");
            while(res.next()){
                Persona ob=new Persona();
                ob.persona_id=res.getInt("id_persona");
                ob.nombre=res.getString("nombre");
                ob.apellido_paterno=res.getString("apellido_paterno");
                ob.apellido_materno=res.getString("apellido_materno");
                ob.apellido_casado=res.getString("apellido_casado");
                ob.telefono=res.getString("telefono");
                ob.fecha_nacimiento=res.getString("fecha_nacimiento");
                ob.correo_electronico=res.getString("correo_electronico");
                array.add(ob);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return array;
    }
    public static Persona SeleccionarPersona(Integer PersonId) throws SQLException {
        System.out.println("variable :"+PersonId);
        Persona ob=new Persona();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select id_persona, nombre, apellido_paterno, apellido_materno, apellido_casado, telefono, fecha_nacimiento, id_direccion, correo_electronico from persona WHERE id_persona="+PersonId);
            if(res.next()){

                ob.persona_id=res.getInt("id_persona");
                ob.nombre=res.getString("nombre");
                ob.apellido_paterno=res.getString("apellido_paterno");
                ob.apellido_materno=res.getString("apellido_materno");
                ob.telefono=res.getString("telefono");
                ob.correo_electronico=res.getString("correo_electronico");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public static Persona EliminarPersona(Integer PersonId) throws SQLException {
        System.out.println("variable :"+PersonId);
        Persona ob=new Persona();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            stat.execute("delete from persona WHERE id_persona="+PersonId);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

    public static Persona ActualizarPersona(Persona ob) throws SQLException {

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            PreparedStatement preesta;
            preesta = con.prepareStatement("UPDATE persona SET nombre =?, apellido_paterno=?, apellido_materno=?, apellido_casado=?, telefono=?, fecha_nacimiento=?, id_direccion=?, correo_electronico=? WHERE id_persona=?");
            preesta.setInt(9, ob.persona_id);
            preesta.setString(1, ob.nombre);
            preesta.setString(2, ob.apellido_paterno);
            preesta.setString(3, ob.apellido_materno);
            preesta.setString(4, ob.apellido_casado);
            preesta.setString(5, ob.telefono);
            preesta.setString(6, ob.fecha_nacimiento);
            preesta.setInt(7, ob.direccion_id);
            preesta.setString(8, ob.correo_electronico);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

}
