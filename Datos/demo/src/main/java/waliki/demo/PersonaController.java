package waliki.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RestController
public class PersonaController {

    @Autowired
    public DataSource dataSource;

    @GetMapping(path= "/persona")
    public List<Persona> getAll() throws SQLException {
        List<Persona> array=new ArrayList<>();

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select id_persona, nombre, apellido_paterno, apellido_materno, apellido_casado, telefono, fecha_nacimiento, id_direccion, correo_electronico from persona ");
            while(res.next()){
                Persona ob=new Persona();
                ob.id_persona=res.getInt("id_persona");
                ob.nombre=res.getString("nombre");
                ob.apellido_paterno=res.getString("apellido_paterno");
                ob.apellido_materno=res.getString("apellido_materno");
                ob.telefono=res.getString("telefono");
                ob.correo_electronico=res.getString("correo_electronico");
                array.add(ob);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return array;
    }

    @GetMapping(path= "/persona/{PersonId}")
    public Persona person(@PathVariable Integer PersonId) throws SQLException {
        System.out.println("variable :"+PersonId);
        Persona ob=new Persona();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select id_persona, nombre, apellido_paterno, apellido_materno, apellido_casado, telefono, fecha_nacimiento, id_direccion, correo_electronico from persona WHERE id_persona="+PersonId);
            if(res.next()){

                ob.id_persona=res.getInt("id_persona");
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

    @PostMapping(path= "/persona")
    public Persona insert_person(@RequestBody Persona ob) throws SQLException {


        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();


            PreparedStatement preesta;
            preesta = con.prepareStatement("INSERT INTO persona(id_persona, nombre, apellido_paterno, apellido_materno, apellido_casado, telefono, fecha_nacimiento, id_direccion, correo_electronico)" + " VALUES (?,?,?,?,?,?,?,?,?)");
            preesta.setInt(1, ob.id_persona);
            preesta.setString(2, ob.nombre);
            preesta.setString(3, ob.apellido_paterno);
            preesta.setString(4, ob.apellido_materno);
            preesta.setString(5, ob.apellido_casado);
            preesta.setString(6, ob.telefono);
            preesta.setInt(7, ob.fecha_nacimiento);
            preesta.setInt(8, ob.id_direccion);
            preesta.setString(9, ob.correo_electronico);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

    @DeleteMapping(path= "/persona/{PersonId}")
    public Persona eliminar_person(@PathVariable Integer PersonId) throws SQLException {
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
    @PutMapping(path= "/persona")
    public Persona actualizar_person(@RequestBody Persona ob) throws SQLException {


        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();


            PreparedStatement preesta;
            preesta = con.prepareStatement("UPDATE persona SET nombre =?, apellido_paterno=?, apellido_materno=?, apellido_casado=?, telefono=?, fecha_nacimiento=?, id_direccion=?, correo_electronico=? WHERE id_persona=?");
            preesta.setInt(9, ob.id_persona);
            preesta.setString(1, ob.nombre);
            preesta.setString(2, ob.apellido_paterno);
            preesta.setString(3, ob.apellido_materno);
            preesta.setString(4, ob.apellido_casado);
            preesta.setString(5, ob.telefono);
            preesta.setInt(6, ob.fecha_nacimiento);
            preesta.setInt(7, ob.id_direccion);
            preesta.setString(8, ob.correo_electronico);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }
}
