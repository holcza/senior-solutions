package bikes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BikeServiceTest {

    BikeService bikeService;

    @BeforeEach
    void init(){
        bikeService = new BikeService();
    }

    @Test
    void getBikes() {
        List<Bike> bikes = bikeService.getBikes();

        assertThat(bikes).extracting(Bike::getId)
                .hasSize(5)
                .contains("FH675");
    }

    @Test
    void getUsers() {

        List<String> users = bikeService.getUsers();

        assertThat(users).hasSize(5)
                .contains("US3334");
    }
}