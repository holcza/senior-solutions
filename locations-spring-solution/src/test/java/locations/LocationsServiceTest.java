package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class LocationsServiceTest {

    LocationsService locationsService;

    @BeforeEach
    void init(){
        locationsService = new LocationsService(new ModelMapper());
    }

    @Test
    void getLocations() {


        List<LocationDto> locations = locationsService.getLocations(Optional.empty()
        );
        List<LocationDto> locations2 = locationsService.getLocations(Optional.of("A")
        );

        assertThat(locations).extracting(LocationDto::getName)
                .containsOnly("A","B","C");
        assertThat(locations2).extracting(LocationDto::getName)
                .containsOnly("A");
    }
}