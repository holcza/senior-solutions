package movies;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDto> getMovies(@RequestParam Optional<String> title){
        return movieService.getMovies(title);
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable("id") long id){
        return movieService.getMovieById(id);
    }

    @PostMapping
    public MovieDto createMovie (@RequestBody CreateMovieCommand command){
        return movieService.createMovie(command);
    }

    @PostMapping("/{id}/rating")
    public MovieDto addRating(@PathVariable("id") long id, @RequestBody NewMovieRatingCommand command){
        return movieService.addRating(id,command);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") long id){
        movieService.deleteMovie(id);
    }
}
