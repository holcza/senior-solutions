package locations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService();
        List<Location> locations = locationsService.getLocations();

        assertThat(locations).extracting(Location::getName)
                .containsOnly("A","B","C");
    }
}