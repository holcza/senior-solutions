package cars;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> listCars(){
        return carService.listCars();
    }

    @GetMapping("/types")
    public List<String> listBrands(){
        return carService.listBrands();
    }
}
