package movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MovieControllerIT {

    @Autowired
    MovieController movieController;

    @Test
    void testCreateMovie(){
        MovieDto movieDto = movieController.createMovie(new CreateMovieCommand("A",120.0));

        assertEquals("A",movieDto.getTitle());
    }
}
