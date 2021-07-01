package movies;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerRestTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void testCreateMovie(){
        MovieDto movieDto =
                restTemplate
                        .postForObject("/api/movies", new CreateMovieCommand("Z",120.0),MovieDto.class);

        assertEquals("Z",movieDto.getTitle());

        MovieDto movieDto2 = restTemplate.getForObject("/api/movies/1",MovieDto.class);

        assertEquals("Z",movieDto.getTitle());
    }


}
