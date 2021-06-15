package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationNestedTest {

    LocationParser locationParser;

    @BeforeEach
    void init(){
        locationParser = new LocationParser();
    }

    @Nested
    class WithEquatorAndPrimeMeridian {

        Location location;

        @BeforeEach
        void init(){
            location = locationParser.parse("Budapest,0,0");
        }

        @Test
        void testIsOnEquator() {
            assertTrue(locationParser.isOnEquator(location));
        }

        @Test
        void testIOnPrimeMeridian() {
            assertTrue(locationParser.isOnPrimeMeridian(location));
        }
    }

    @Nested
    class WithoutEquatorAndPrimeMeridian {

        Location location;

        @BeforeEach
        void init(){
            location = locationParser.parse("Budapest,47.497912,19.040235");
        }

        @Test
        void testIsOnEquator() {
            assertFalse(locationParser.isOnEquator(location));
        }

        @Test
        void testIOnPrimeMeridian() {
            assertFalse(locationParser.isOnPrimeMeridian(location));
        }

    }
}
