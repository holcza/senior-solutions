package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DistanceServiceTest {

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    DistanceService distanceService;

    @Test
    void testCalculateDistance(){
        when(locationRepository.findByName("A")).thenReturn(Optional.empty());
        when(locationRepository.findByName("B")).thenReturn(Optional.of(new Location("B",10,13)));
        when(locationRepository.findByName("C")).thenReturn(Optional.of(new Location("C",10,20)));


        assertEquals(Optional.empty(),distanceService.calculateDistance("A","B"));
        assertEquals(766000,distanceService.calculateDistance("B","C").get(),1000);

        verify(locationRepository).findByName("A");
    }
}
