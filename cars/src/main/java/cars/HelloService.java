package cars;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

    public String sayHello(){
        return "Üdvölünk az oldalon";
    }
}
