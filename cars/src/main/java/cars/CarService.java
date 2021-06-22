package cars;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private List<Car> cars;

    public CarService() {
        this.cars = List.of(
                new Car("Ford","Fiesta",10,CarStatus.KIVALO,
                        List.of(new KmState(LocalDate.of(2020,10,1),100000),
                                new KmState(LocalDate.of(2021,01,02),150000))),
                new Car("Ford","Mondeo",1,CarStatus.KIVALO,
                        List.of(new KmState(LocalDate.of(2021,1,1),10000),
                                new KmState(LocalDate.of(2021,04,05),15000)))
        );
    }


    public List<Car> listCars() {
        return cars;
    }

    public List<String> listBrands() {
        return cars.stream()
                .map(c->c.getBrand()).distinct()
                .collect(Collectors.toList());
    }
}
