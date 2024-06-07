package pl.wsb.lesinskibartosz.LibrarySystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.AuthorRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Author;
import pl.wsb.lesinskibartosz.LibrarySystem.service.AuthorService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //get all authors
    @GetMapping("")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.findAll();
        return ResponseEntity.ok(authors);
    }

    // get author by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with this ID not found");
        }
    }

    //add author
    @PostMapping("/add")
    public ResponseEntity<?> addAuthors(@Valid @RequestBody List<Author> authors) {
        List<Author> addedAuthors = authorService.addAuthors(authors);
        if (addedAuthors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: All of the authors already exist, you need to add at least one unique author");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedAuthors);
        }
    }

    //delete author
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        if (authorService.existsById(id)) {
            authorService.deleteById(id);
            return ResponseEntity.ok("Author deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author with this ID not found");
        }
    }
}

