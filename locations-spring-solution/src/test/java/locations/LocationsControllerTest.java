package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
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

        List<Location> location = List.of(
                new Location(1L,"A",23,34)
        );

        ModelMapper modelMapper = new ModelMapper();
        Type targetListType = new TypeToken<List<LocationDto>>() {
        }.getType();
        List<LocationDto> locationDto = modelMapper.map(location,targetListType);

        when(locationsService.getLocations(Optional.of("A"))).thenReturn(locationDto);

        assertThat(locationsController.getLocations(Optional.of("A"))).isEqualTo(
                locationDto
        );

    }
}