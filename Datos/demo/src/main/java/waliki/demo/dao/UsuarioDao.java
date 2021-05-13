package waliki.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waliki.demo.dto.Usuario;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioDao {

    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    public DataSource dataSource;

    public Usuario CrearUsuario(Usuario ob)throws SQLException {
        ob.usuario_id=sequenceDao.getLLaveprincipal("usuario");
        try{
            Connection con=dataSource.getConnection();

            PreparedStatement preesta;
            preesta = con.prepareStatement("INSERT INTO usuario(usuario_id,tipo_usuario_id,usuario,contrasena,fecha_registro,codigo_verificacion,persona_id)" + " VALUES (?,?,?,?,TO_DATE(?,'YYYYMMDD'),?,?)");
            preesta.setInt(1, ob.usuario_id);
            preesta.setInt(2, ob.tipo_usuario_id);
            preesta.setString(3, ob.usuario);
            preesta.setString(4, ob.contrasena);
            preesta.setString(5, ob.fecha_registro);
            preesta.setString(6, ob.codigo_verificacion);
            preesta.setInt(7, ob.persona_id);

            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public List<Usuario> SeleccionarTodosUsuarios() throws SQLException {
        List<Usuario> array=new ArrayList<>();

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select usuario_id,tipo_usuario_id,usuario,contrasena,fecha_registro,codigo_verificacion,persona_id from usuario ");
            while(res.next()){
                Usuario ob = new Usuario();
                ob.usuario_id=res.getInt("usuario_id");
                ob.tipo_usuario_id=res.getInt("tipo_usuario_id");
                ob.usuario=res.getString("usuario");
                ob.contrasena=res.getString("contrasena");
                ob.fecha_registro=res.getString("fecha_registro");
                ob.codigo_verificacion=res.getString("codigo_verificacion");
                ob.persona_id=res.getInt("persona_id");
                array.add(ob);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return array;
    }
    public Usuario SeleccionarUsuario(Integer usuarioId) throws SQLException {

        Usuario ob=new Usuario();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select usuario_id,tipo_usuario_id,usuario,contrasena,fecha_registro,codigo_verificacion,persona_id from usuario WHERE usuario_id="+usuarioId);
            if(res.next()){
                ob.usuario_id=res.getInt("usuario_id");
                ob.tipo_usuario_id=res.getInt("tipo_usuario_id");
                ob.usuario=res.getString("usuario");
                ob.contrasena=res.getString("contrasena");
                ob.fecha_registro=res.getString("fecha_registro");
                ob.codigo_verificacion=res.getString("codigo_verificacion");
                ob.persona_id=res.getInt("persona_id");
            }else{
                ob=null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public Usuario EliminarUsuario(Integer usuarioId) throws SQLException {

        Usuario ob=new Usuario();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            stat.execute("delete from usuario WHERE usuario_id="+usuarioId);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

    public Usuario ActualizarUsuario(Usuario ob) throws SQLException {

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            PreparedStatement preesta;
            preesta = con.prepareStatement("UPDATE usuario SET tipo_usuario_id=?,usuario=?,contrasena=?,fecha_registro=?,codigo_verificacion=?,persona_id=? WHERE usuario_id=?");
            preesta.setInt(7, ob.usuario_id);
            preesta.setInt(1, ob.tipo_usuario_id);
            preesta.setString(2, ob.usuario);
            preesta.setString(3, ob.contrasena);
            preesta.setString(4, ob.fecha_registro);
            preesta.setString(5, ob.codigo_verificacion);
            preesta.setInt(6, ob.persona_id);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

}
