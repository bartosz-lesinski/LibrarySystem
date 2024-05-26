package pl.wsb.lesinskibartosz.LibrarySystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.wsb.lesinskibartosz.LibrarySystem.Repository.BookRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Book;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //get all books
    @GetMapping("")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // get single bookById
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with this ID not found");
        }
    }

    // get single book by title
    @GetMapping("/title/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        Optional<Book> bookOptional = bookRepository.findByTitle(title);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with this title not found");
        }
    }

    //get list of books by year
    @GetMapping("/publish_year/{year}")
    public ResponseEntity<?> getBooksByPublishYear(@PathVariable int year) {
        List<Book> books = bookRepository.findByPublishYear(year);

        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Books published in " + year + " not found");
        }
    }

    //get single book by isbn
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ISBN " + isbn + " not found");
        }
    }

    //get list of available books
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableBooks() {
        List<Book> books = bookRepository.findByAvailable(true);

        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No available books found");
        }
    }

    //get list of unavailable books
    @GetMapping("/unavailable")
    public ResponseEntity<?> getUnavailableBooks() {
        List<Book> books = bookRepository.findByAvailable(false);

        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No unavailable books found");
        }
    }

    //get list of books by author id
    @GetMapping("/author/{authorId}")
    public ResponseEntity<?> getBooksByAuthorId(@PathVariable Long authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);

        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Books by author with ID " + authorId + " not found");
        }
    }

    //add book
    @PostMapping("/add/")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book addedBook = bookRepository.save(book);
        return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
    }

    //delete book by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }

        bookRepository.deleteById(id);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }
}