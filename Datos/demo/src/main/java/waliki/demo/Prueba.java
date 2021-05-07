package waliki.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Prueba {
    @GetMapping(path="/hola")
    public String helloworl(){

        return("Hola mundo");
    }
}
