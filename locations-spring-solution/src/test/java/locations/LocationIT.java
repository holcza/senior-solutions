package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class LocationIT {

    @Autowired
    LocationsService locationsService;

    @Test
    void testGetLocations(){
        assertThat(locationsService.getLocations()).toString().equals(
                "[Location{id=1, name='A', lat=23.0, lon=67.0}, " +
                        "Location{id=2, name='B', lat=56.0, lon=72.0}, " +
                        "Location{id=3, name='C', lat=4.0, lon=-67.0}]"
        );
    }
}
