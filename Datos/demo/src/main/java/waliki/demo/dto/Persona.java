package waliki.demo.dto;

import org.springframework.web.bind.annotation.RestController;

@RestController

public class Persona {
    public Integer persona_id;
    public Integer direccion_id;
    public Integer tipo_identificacion_id;
    public String nombre;
    public String apellido_paterno;
    public String apellido_materno;
    public String apellido_casado;
    public String telefono;
    public String correo_electronico;
    public String numero_identificacion;
    public String fecha_nacimiento;


}
