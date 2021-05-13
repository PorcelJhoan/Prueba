package waliki.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waliki.demo.dto.Emprendedor;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmprendedorDao {
    @Autowired
    private SequenceDao sequenceDao;

    @Autowired
    public DataSource dataSource;

    public Emprendedor CrearEmprendedor(Emprendedor ob)throws SQLException {
        ob.emprendedor_id=sequenceDao.getLLaveprincipal("emprendedor");
        try{
            Connection con=dataSource.getConnection();

            PreparedStatement preesta;
            preesta = con.prepareStatement("INSERT INTO emprendedor(emprendedor_id,imagen_id,tipo_emprendimiento_id,contrato_id,usuario_id)" + " VALUES (?,?,?,?,?)");
            preesta.setInt(1, ob.emprendedor_id);
            preesta.setInt(2, ob.imagen_id);
            preesta.setInt(3, ob.tipo_emprendimiento_id);
            preesta.setInt(4, ob.contrato_id);
            preesta.setInt(5, ob.usuario_id);

            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public List<Emprendedor> SeleccionarEmprendedores() throws SQLException {
        List<Emprendedor> array=new ArrayList<>();

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select emprendedor_id,imagen_id,tipo_emprendimiento_id,contrato_id,usuario_id from emprendedor ");
            while(res.next()){
                Emprendedor ob = new Emprendedor();
                ob.emprendedor_id=res.getInt("emprendedor_id");
                ob.imagen_id=res.getInt("imagen_id");
                ob.tipo_emprendimiento_id=res.getInt("tipo_emprendimiento_id");
                ob.contrato_id=res.getInt("contrato_id");
                ob.usuario_id=res.getInt("usuario_id");
                array.add(ob);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return array;
    }
    public Emprendedor SeleccionarEmprendedor(Integer usuarioId) throws SQLException {

        Emprendedor ob=new Emprendedor();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            ResultSet res= stat.executeQuery("select emprendedor_id,imagen_id,tipo_emprendimiento_id,contrato_id,usuario_id from emprendedor WHERE emprendedor_id="+usuarioId);
            if(res.next()){
                ob.emprendedor_id=res.getInt("emprendedor_id");
                ob.imagen_id=res.getInt("imagen_id");
                ob.tipo_emprendimiento_id=res.getInt("tipo_emprendimiento_id");
                ob.contrato_id=res.getInt("contrato_id");
                ob.usuario_id=res.getInt("usuario_id");
            }else{
                ob=null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ob;
    }
    public Emprendedor EliminarEmprendedor(Integer emprendedorId) throws SQLException {

        Emprendedor ob=new Emprendedor();
        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            stat.execute("delete from emprendedor WHERE emprendedor_id="+emprendedorId);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }

    public Emprendedor ActualizarEmprendedor(Emprendedor ob) throws SQLException {

        try{
            Connection con=dataSource.getConnection();
            Statement stat =con.createStatement();
            PreparedStatement preesta;
            preesta = con.prepareStatement("UPDATE emprendedor SET emprendedor_id=?,imagen_id=?,tipo_emprendimiento_id=?,contrato_id=?,usuario_id=? WHERE usuario_id=?");
            preesta.setInt(5, ob.emprendedor_id);
            preesta.setInt(1, ob.imagen_id);
            preesta.setInt(2, ob.tipo_emprendimiento_id);
            preesta.setInt(3, ob.contrato_id);
            preesta.setInt(4, ob.usuario_id);
            preesta.executeUpdate();
            preesta.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ob;
    }
}
