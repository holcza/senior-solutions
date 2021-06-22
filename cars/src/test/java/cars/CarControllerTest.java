package cars;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @Test
    void listCars() {
        when(carService.listCars()).thenReturn(List.of(
                new Car("Ford", "Fiesta", 10, CarStatus.KIVALO,
                        List.of(new KmState(LocalDate.of(2020, 10, 1), 100000),
                                new KmState(LocalDate.of(2021, 01, 02), 150000))),
                new Car("Ford", "Mondeo", 1, CarStatus.KIVALO,
                        List.of(new KmState(LocalDate.of(2021, 1, 1), 10000),
                                new KmState(LocalDate.of(2021, 04, 05), 15000)))));

        List<Car> cars = carController.listCars();

        assertThat(cars).extracting(Car::getType)
                .filteredOn(e->e.equals("Mondeo"))
                .size().isEqualTo(1);

        verify(carService,times(1)).listCars();
    }

    @Test
    void listBrands() {

        when(carService.listBrands()).thenReturn(List.of("Ford"));

        List<String> brands = carController.listBrands();

        assertThat(brands)
                .hasSize(1)
                .containsOnly("Ford");
        verify(carService).listBrands();
    }
}
