package locations;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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

    private Location[] locations = {new Location("A", 0, 3),
            new Location("B", 0, 5),
            new Location("C", 3, 5)};

    private boolean[] results = {true, true, false};


    @RepeatedTest(value = 3,
            name = "To test checking whether location is on equator {currentRepetition} / {totalRepetitions}")
    void testIsOnEquator(RepetitionInfo repetitionInfo) {

        assertEquals(results[repetitionInfo.getCurrentRepetition()-1],
                locationParser.isOnEquator(locations[repetitionInfo.getCurrentRepetition()-1]));
    }

    @Test
    @DisplayName("To test checking whether location is on prime meridian")
    void testIsOnPrimeMeridian() {

        assertTrue(locationParser.isOnPrimeMeridian(new Location("Budapest", 47.497912, 0)));
        assertFalse(locationParser.isOnPrimeMeridian(new Location("Budapest", 47.497912, 19.040235)));
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
