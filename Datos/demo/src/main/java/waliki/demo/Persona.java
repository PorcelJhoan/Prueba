package waliki.demo;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class Persona {
    public Integer id_persona;
    public String nombre;
    public String apellido_paterno;
    public String apellido_materno;
    public String apellido_casado;
    public String telefono;
    public Integer fecha_nacimiento;
    public Integer id_direccion;
    public String correo_electronico;

}
