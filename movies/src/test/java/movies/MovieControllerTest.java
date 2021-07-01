package movies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @Mock
    MovieService movieService;

    @InjectMocks
    MovieController movieController;

    @Test
    void testCreateMovie(){
        when(movieService.createMovie(any())).thenReturn(new MovieDto("A",120.0,5.5));

        MovieDto movieDto = movieController.createMovie(new CreateMovieCommand("A",120.0));

        assertEquals("A",movieDto.getTitle());
    }
}
