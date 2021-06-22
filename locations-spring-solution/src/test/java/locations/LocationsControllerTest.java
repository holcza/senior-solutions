package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocations() {

        List<LocationDto> locationDtos = List.of(
                new LocationDto(1L,"A",23,34)
        );

        when(locationsService.getLocations(Optional.of("A"))).thenReturn(locationDtos);

        assertThat(locationsController.getLocations(Optional.of("A"))).isEqualTo(
                locationDtos
        );

    }
}