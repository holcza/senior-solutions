package bikes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {

    @Mock
    BikeService bikeService;

    @InjectMocks
    BikeController controller;

    @Test
    void getBikes() {
        when(bikeService.getBikes()).thenReturn(List.of(
                new Bike("FH675","US3434", LocalDateTime.of(2000,11,23,2,34,54),0.8),
                new Bike("FH676","US3435", LocalDateTime.of(2001,11,23,2,34,54),0.9)));

        assertThat(controller.getBikes()).extracting(Bike::getId)
                .hasSize(2)
                .contains("FH675","FH676");

        verify(bikeService).getBikes();
    }

    @Test
    void getUsers() {
        when(bikeService.getUsers()).thenReturn(List.of(
                "US3434", "US3435"));

        assertThat(controller.getUsers())
                .hasSize(2)
                .contains("US3434", "US3435");

        verify(bikeService).getUsers();
    }
}