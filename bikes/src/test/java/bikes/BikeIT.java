package bikes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
public class BikeIT {

    @Autowired
    BikeController bikeController;

    @Test
    public void getBikes() {
        List<Bike> bikes = bikeController.getBikes();

        assertThat(bikes).extracting(Bike::getId)
                .hasSize(5)
                .contains("FH675");
    }

    @Test
    public void getUsers() {
        List<String> users = bikeController.getUsers();

        assertThat(users).hasSize(5)
                .contains("US3334");


    }
}
