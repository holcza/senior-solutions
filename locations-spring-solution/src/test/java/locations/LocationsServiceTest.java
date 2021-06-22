package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationsServiceTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    LocationsService locationsService;

    @Test
    void getLocations() {

        List<LocationDto> locations = locationsService.getLocations(Optional.empty());

        assertThat(locations).extracting(LocationDto::getName)
                .containsOnly("A","B","C");
    }
}