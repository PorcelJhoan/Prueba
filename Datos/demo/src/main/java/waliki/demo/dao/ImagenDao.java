package waliki.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waliki.demo.dto.Imagen;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class ImagenDao {
    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    public DataSource dataSource;

    public Imagen CrearImagen(Imagen ob)throws SQLException {
        ob.imagen_id=sequenceDao.getLLaveprincipal("imagen");
        try{
            Connection con=dataSource.getConnection();
            PreparedStatement preesta;
            preesta = con.prepareStatement("INSERT INTO imagen(imagen_id,src_imagen,nombre)" + " VALUES (?,?,?)");
            preesta.setInt(1, ob.imagen_id);
            preesta.setString(2, ob.src_imagen);
            preesta.setString(3, ob.nombre);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public List<Imagen> SeleccionarTodasImagenes() throws SQLException {
        List<Imagen> array=new ArrayList<>();

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select imagen_id,src_imagen,nombre from imagen ");
            while(res.next()){
                Imagen ob = new Imagen();
                ob.imagen_id=res.getInt("imagen_id");
                ob.src_imagen=res.getString("src_imagen");
                ob.nombre=res.getString("nombre");
                array.add(ob);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return array;
    }
    public Imagen SeleccionarImagen(Integer imagenId) throws SQLException {

        Imagen ob=new Imagen();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select imagen_id,src_imagen,nombre from imagen WHERE imagen_id="+imagenId);
            if(res.next()){
                ob.imagen_id=res.getInt("imagen_id");
                ob.src_imagen=res.getString("src_imagen");
                ob.nombre=res.getString("nombre");
            }else{
                ob=null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public Imagen EliminarImagen(Integer ImagenId) throws SQLException {

        Imagen ob=new Imagen();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            stat.execute("delete from imagen WHERE imagen_id="+ImagenId);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

    public Imagen ActualizarImagen(Imagen ob) throws SQLException {

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            PreparedStatement preesta;
            preesta = con.prepareStatement("UPDATE imagen SET src_imagen=?,nombre=? WHERE imagen_id=?");
            preesta.setInt(3, ob.imagen_id);
            preesta.setString(1, ob.src_imagen);
            preesta.setString(2, ob.nombre);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }
}
