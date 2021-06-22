package cars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class CarsIT {

    @Autowired
    CarController carController;


    @Test
    void testListCars(){

        List<Car> cars = carController.listCars();

        assertThat(cars).extracting(Car::getType)
                .filteredOn(e->e.equals("Mondeo"))
                .size().isEqualTo(1);

    }

    @Test
    void testListBrands(){

        List<String> brands = carController.listBrands();

        assertThat(brands)
                .size().isEqualTo(2);
        assertThat(brands)
                .containsOnly("Ford");
    }
}
