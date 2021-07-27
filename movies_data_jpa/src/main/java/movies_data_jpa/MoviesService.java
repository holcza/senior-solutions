package movies_data_jpa;

import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class MoviesService {

    private MoviesRepo moviesRepo;

    private ModelMapper modelMapper;

    public List<MovieDto> listMovies() {
        List<Movie> movies = moviesRepo.findAll();
        Type type = new TypeToken<List<MovieDto>>(){}.getType();
        return modelMapper.map(movies,type);
    }

    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(command.getTitle());

        moviesRepo.save(movie);

        return modelMapper.map(movie,MovieDto.class);
    }

    @Transactional
    public MovieDto addRatingToMovie(Long id, CreateRatingCommand command) {
        Movie movie = moviesRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Can not find movie"));
        movie.addRating(command.getRating());
        return modelMapper.map(movie,MovieDto.class);
    }
}
