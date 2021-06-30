package movies;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private ModelMapper modelMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Movie> movies = new ArrayList<>();

    public MovieService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<MovieDto> getMovies(Optional<String> title) {
        Type targetListType =new TypeToken<List<MovieDto>>(){}.getType();

        List<Movie> filteredMovies = movies.stream()
                .filter(m->title.isEmpty()||m.getTitle().equals(title.get()))
                .collect(Collectors.toList());

        return modelMapper.map(filteredMovies,targetListType);
    }

    public MovieDto getMovieById(long id) {
        Movie movie = findById(id);
        return modelMapper.map(movie,MovieDto.class);
    }


    public MovieDto createMovie(CreateMovieCommand command) {
        Movie movie = new Movie(idGenerator.incrementAndGet(),command.getTitle(),command.getLength());

        movies.add(movie);

        return modelMapper.map(movie,MovieDto.class);
    }


    public MovieDto addRating(long id, NewMovieRatingCommand command) {
        Movie movie = findById(id);

        movie.addRating(command.getRating());

        return modelMapper.map(movie,MovieDto.class);
    }

    public void deleteMovie(long id) {
        Movie movie = findById(id);

        movies.remove(movie);
    }

    private Movie findById (long id){
        return movies.stream()
                .filter(m->m.getId()==id)
                .findAny()
                .orElseThrow(()->new IllegalArgumentException("Can not find ID : "+id));
    }
}
