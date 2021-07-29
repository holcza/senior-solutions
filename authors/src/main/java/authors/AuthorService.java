package authors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository repository;

    private ModelMapper modelMapper;

    public List<AuthorDto> getAllAuthors(){
        return repository.findAll().stream()
                .map(m->modelMapper.map(m,AuthorDto.class))
                .collect(Collectors.toList());
    }

    public AuthorDto createAuthor(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        repository.save(author);
        return modelMapper.map(author,AuthorDto.class);
    }

    @Transactional
    public AuthorDto createBook(long id, CreateBookCommand command) {
        Book book = new Book(command.getIsbn(), command.getTitle());
        Author author = repository.findById(id).orElseThrow(()->new IllegalArgumentException("Can not find author"));
        author.addBook(book);
        return modelMapper.map(author,AuthorDto.class);
    }

    public void deleteAuthor(long id) {
        Author author = repository.findById(id).orElseThrow(()->new IllegalArgumentException("Can not find author"));
        repository.delete(author);
    }
}
