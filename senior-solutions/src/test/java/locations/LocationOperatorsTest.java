package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@LocationOperationsFeatureTest
public class LocationOperatorsTest {

    LocationOperators locationOperators;
    List<Location> locations;

    @BeforeEach
    void init() {
        locationOperators = new LocationOperators();
        locations = List.of(new Location("London", 51.507351, -0.127758),
                new Location("Budapest", 47.497912, 19.040235),
                new Location("Sydney", -33.868820, 151.209290));
    }

    @Test
    @DisplayName("To test filtering on north locations")
    void testFilterOnNorth() {

        assertEquals(List.of("London", "Budapest"), locationOperators.filterOnNorth(locations)
                .stream().map(Location::getName)
                .collect(Collectors.toList()));
    }
}
