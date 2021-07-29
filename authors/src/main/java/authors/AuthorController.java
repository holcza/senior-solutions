package authors;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody CreateAuthorCommand command){
        return authorService.createAuthor(command);
    }

    @PostMapping("/{id}/books")
    public AuthorDto createBook(@PathVariable("id") long id, @RequestBody CreateBookCommand command){
        return authorService.createBook(id,command);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable("id") long id){
        authorService.deleteAuthor(id);
    }
}
