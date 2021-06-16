package locations;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SoftAssertionsExtension.class)
public class LocationServiceTest {

    LocationService locationService;

    List<Location> locationList;

    @BeforeEach
    void init() {
        locationService = new LocationService();

        locationList = List.of(new Location("A", 12, 13),
                new Location("B", 32, 56),
                new Location("C", 67, -12));
    }

    @TempDir
    Path path;

    @Test
    void testWriteLocations() throws IOException {
        Path file = path.resolve("location.csv");

        locationService.writeLocations(file, locationList);

        List<String> result = Files.readAllLines(file);
        assertEquals(12, Double.parseDouble(result.get(0).split(",")[1]));
    }

    @Test
    void testReadLocationsFromCsv(SoftAssertions softly) {
        List<Location> locations = locationService.readLocationsFromCsv(Path.of("src/test/resources/locations.csv"));
        softly.assertThat(locations)
                .hasSize(4)
                .extracting(Location::getName,Location::getLat,Location::getLon)
                .contains(tuple("B",47.497912,19.040235),
                        tuple("D",78.245,55.678));
        softly.assertThat(locations)
                .filteredOn(e->e.getName().equals("A"))
                .extracting(Location::getLat)
                .containsOnly(51.507351);
    }
}
