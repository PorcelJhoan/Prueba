package waliki.demo.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waliki.demo.dao.Tipo_identificacionDao;
import waliki.demo.dto.Tipo_identificacion;


import java.sql.SQLException;
import java.util.List;

@Service
public class GestionTipo_IdentificacionBl {

    @Autowired
    Tipo_identificacionDao tipo_identificacionDao;

    public Tipo_identificacion CrearTipoIdentificacion(Tipo_identificacion tipoiden)throws SQLException {
        return tipo_identificacionDao.CrearTipoIdentificacion(tipoiden);
    }
    public List<Tipo_identificacion> SeleccionarTipoIdentificaciones() throws SQLException {
        return tipo_identificacionDao.SeleccionarTiposIdentificacion();
    }
    public Tipo_identificacion SeleccionarTipoIdentificacion(Integer tipoId) throws SQLException {

        return tipo_identificacionDao.SeleccionarIdentificacion(tipoId);
    }
    public Tipo_identificacion EliminarTipoIdentificacion(Integer tipoId) throws SQLException {

        return tipo_identificacionDao.EliminarTipoIdentificacion(tipoId);
    }

    public Tipo_identificacion ActualizarTipoIdentificacion(Tipo_identificacion tipoiden) throws SQLException {

        return tipo_identificacionDao.ActualizarTipoIdentificacion(tipoiden);
    }

}
