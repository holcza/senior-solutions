package locations;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LocationTest<locationParser> {

    LocationParser locationParser;

    @BeforeEach
    void init() {

        locationParser = new LocationParser();
    }

    @Test
    @DisplayName("To test empty input for parsing location")
    void testParseEmpty() {

        IllegalArgumentException iae = assertThrows(IllegalArgumentException.class,
                () -> locationParser.parse(""));

        assertEquals("Not valid input", iae.getMessage());

    }

    @Test
    @DisplayName("To test parsing location method")
    void testParse() {

        Location location = locationParser.parse("Budapest,47.497912,19.040235");
        Location location2 = locationParser.parse("Budapest,47.497912,19.040235");

        assertAll(
                () -> assertEquals("Budapest", location.getName()),
                () -> assertEquals(47.497912, location.getLat()),
                () -> assertEquals(19.040235, location.getLon())

        );

        assertNotSame(location, location2);

    }

    private Object[][] values = {{new Location("A", 0, 3), true},
            {new Location("B", 0, 5), true},
            {new Location("C", 3, 5), false}};


    @RepeatedTest(value = 3,
            name = "To test checking whether location is on equator {currentRepetition} / {totalRepetitions}")
    void testIsOnEquator(RepetitionInfo repetitionInfo) {

        assertEquals(values[repetitionInfo.getCurrentRepetition() - 1][1],
                locationParser.isOnEquator((Location) values[repetitionInfo.getCurrentRepetition() - 1][0]));
    }

    @TestFactory
    Stream<DynamicTest> testIsOnEquatorWithDynamicTest() {
        return Stream.of(new Object[][]{{new Location("A", 0, 3), true},
                {new Location("B", 0, 5), true},
                {new Location("C", 3, 5), false}})
                .map(item -> dynamicTest("To test checking whether location is on equator - " + item[1],
                        () -> assertEquals(item[1], locationParser.isOnEquator((Location) item[0]))));
    }

    static Stream<Arguments> isPrimeMeridian() {
        return Stream.of(
                arguments(new Location("Budapest", 47.497912, 0), true),
                arguments(new Location("Budapest", 47.497912, 19.040235), false)
        );
    }

    @ParameterizedTest(name = "To test checking whether location is on prime meridian - {1}")
    @MethodSource("isPrimeMeridian")
    void testIsOnPrimeMeridian(Location location, boolean isOnPrimeMeridian) {

        assertEquals(isOnPrimeMeridian, locationParser.isOnPrimeMeridian(location));
    }

    @ParameterizedTest(name = "To test calculating distance {0}-{1} vs {2}-{3} is {4}")
    @CsvFileSource(resources = "/distances.csv")
    void testDistanceFromCSV(double lat, double lon, double lat2, double lon2, double distance) {

        assertEquals(distance, new Location("", lat, lon)
                .distanceFrom(new Location("", lat2, lon2)), 1000);
    }

    @Test
    @DisplayName("To test calculating distance from other location")
    void testDistanceFrom() {

        assertEquals(1449000,
                new Location("London", 51.507351, -0.127758)
                        .distanceFrom(new Location("Budapest", 47.497912, 19.040235)), 1000);
    }

    @Test
    @DisplayName("To test inputs for locations")
    void testLocationInputs() {

        IllegalArgumentException iae =
                assertThrows(IllegalArgumentException.class, () -> new Location("", -100, -100));
        IllegalArgumentException iae2 =
                assertThrows(IllegalArgumentException.class, () -> new Location("", 100, -100));
        assertEquals("Not correct latitude", iae.getMessage());
        assertEquals("Not correct latitude", iae2.getMessage());
        IllegalArgumentException iae3 =
                assertThrows(IllegalArgumentException.class, () -> new Location("", -90, -190));
        IllegalArgumentException iae4 =
                assertThrows(IllegalArgumentException.class, () -> new Location("", 90, 190));
        assertEquals("Not correct longitude", iae3.getMessage());
        assertEquals("Not correct longitude", iae4.getMessage());
    }
}
