package cars;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CarServiceTest {


    @Test
    void testListCars(){

        CarService service = new CarService();

        List<Car> cars = service.listCars();

        assertThat(cars).extracting(Car::getType)
                .filteredOn(e->e.equals("Mondeo"))
                .size().isEqualTo(1);

    }

    @Test
    void testListBrands(){

        CarService carService = new CarService();

        List<String> brands = carService.listBrands();

        assertThat(brands)
                .size().isEqualTo(2);
        assertThat(brands)
                .containsOnly("Ford");
    }
}
