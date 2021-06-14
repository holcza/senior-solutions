package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
                ()-> assertEquals("Budapest", location.getName()),
                ()-> assertEquals(47.497912, location.getLat()),
                ()-> assertEquals(19.040235, location.getLon())

        );

        assertNotSame(location, location2);

    }

    @Test
    @DisplayName("To test checking whether location is on equator")
    void testIsOnEquator() {

        assertTrue(locationParser.isOnEquator(new Location("Budapest", 0, 19.040235)));
        assertFalse(locationParser.isOnEquator(new Location("Budapest", 47.497912, 19.040235)));
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
                new Location("London",51.507351,-0.127758)
                        .distanceFrom(new Location("Budapest", 47.497912, 19.040235)),1000);
    }
}
