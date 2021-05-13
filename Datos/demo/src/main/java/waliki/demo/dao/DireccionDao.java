package waliki.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waliki.demo.dto.Direccion;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DireccionDao {
    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    public DataSource dataSource;

    public Direccion CrearDireccion(Direccion ob)throws SQLException {
        ob.direccion_id=sequenceDao.getLLaveprincipal("direccion");
        try{
            Connection con=dataSource.getConnection();
            PreparedStatement preesta;
            preesta = con.prepareStatement("INSERT INTO direccion(direccion_id,zona,calle,Longitud,Latitud,numero)" + " VALUES (?,?,?,?,?,?)");
            preesta.setInt(1, ob.direccion_id);
            preesta.setString(2, ob.zona);
            preesta.setString(3, ob.calle);
            preesta.setDouble(4, ob.Longitud);
            preesta.setDouble(5, ob.Latitud);
            preesta.setString(6, ob.numero);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public List<Direccion> SeleccionarTodasDirecciones() throws SQLException {
        List<Direccion> array=new ArrayList<>();

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select direccion_id,zona,calle,Longitud,Latitud,numero from direccion ");
            while(res.next()){
                Direccion ob = new Direccion();
                ob.direccion_id=res.getInt("direccion_id");
                ob.zona=res.getString("zona");
                ob.calle=res.getString("calle");
                ob.Longitud=res.getDouble("Longitud");
                ob.Latitud=res.getDouble("Latitud");
                ob.numero=res.getString("numero");
                array.add(ob);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return array;
    }
    public Direccion SeleccionarDireccion(Integer direccionId) throws SQLException {

        Direccion ob=new Direccion();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select direccion_id,zona,calle,Longitud,Latitud,numero from direccion WHERE direccion_id="+direccionId);
            if(res.next()){
                ob.direccion_id=res.getInt("direccion_id");
                ob.zona=res.getString("zona");
                ob.calle=res.getString("calle");
                ob.Longitud=res.getDouble("Longitud");
                ob.Latitud=res.getDouble("Latitud");
                ob.numero=res.getString("numero");
            }else{
                ob=null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public Direccion EliminarDireccion(Integer direccionId) throws SQLException {

        Direccion ob=new Direccion();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            stat.execute("delete from direccion WHERE direccion_id="+direccionId);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

    public Direccion ActualizarDireccion(Direccion ob) throws SQLException {

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            PreparedStatement preesta;
            preesta = con.prepareStatement("UPDATE direccion SET zona=?,calle=?,Longitud=?,Latitud=?,numero=? WHERE direccion_id=?");
            preesta.setInt(6, ob.direccion_id);
            preesta.setString(1, ob.zona);
            preesta.setString(2, ob.calle);
            preesta.setDouble(3, ob.Longitud);
            preesta.setDouble(4, ob.Latitud);
            preesta.setString(5, ob.numero);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }
}
