package waliki.demo.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waliki.demo.dao.DireccionDao;
import waliki.demo.dto.Direccion;


import java.sql.SQLException;
import java.util.List;

@Service
public class GestionDireccionBl {
    @Autowired
    DireccionDao direccionDao;

    public Direccion CrearDireccion(Direccion direccion)throws SQLException {
        return direccionDao.CrearDireccion(direccion);
    }
    public List<Direccion> SeleccionarTodasDirecciones() throws SQLException {
        return direccionDao.SeleccionarTodasDirecciones();
    }
    public Direccion SeleccionarDireccion(Integer direccionId) throws SQLException {

        return direccionDao.SeleccionarDireccion(direccionId);
    }
    public Direccion EliminarDireccion(Integer direccionId) throws SQLException {

        return direccionDao.EliminarDireccion(direccionId);
    }

    public Direccion ActualizarDireccion(Direccion ob) throws SQLException {

        return direccionDao.ActualizarDireccion(ob);
    }
}
