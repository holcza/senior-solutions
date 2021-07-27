package movies_data_jpa;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping
    public List<MovieDto> listMovies (){
        return moviesService.listMovies();
    }

    @PostMapping
    public MovieDto createMovie(@RequestBody CreateMovieCommand command){
        return moviesService.createMovie(command);
    }

    @PostMapping("{id}/rating")
    public MovieDto addRatingToMovie (@PathVariable("id") long id,@RequestBody CreateRatingCommand command){
        return moviesService.addRatingToMovie(id,command);
    }
}
