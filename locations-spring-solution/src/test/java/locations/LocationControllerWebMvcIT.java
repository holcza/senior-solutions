package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest (controllers = LocationsController.class)
public class LocationControllerWebMvcIT {

    @MockBean
    LocationsController controller;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getLocationsTest() throws Exception{
        when(controller.getLocations(any())).thenReturn(
                List.of(new LocationDto(1L,"A", 23, 67),
                        new LocationDto(2L,"B", 45, 78))
        );

        mockMvc.perform(get("/locations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",equalTo("A")));


    }
}

