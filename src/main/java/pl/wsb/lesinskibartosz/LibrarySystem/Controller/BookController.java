package pl.wsb.lesinskibartosz.LibrarySystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Book;
import pl.wsb.lesinskibartosz.LibrarySystem.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //get all books
    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    //get book by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with this ID not found");
        }
    }

    //get book by title
    @GetMapping("/title/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title) {
        Book book = bookService.findByTitle(title);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with this title not found");
        }
    }

    //get list of books by publish year
    @GetMapping("/publish_year/{year}")
    public ResponseEntity<?> getBooksByPublishYear(@PathVariable int year) {
        List<Book> books = bookService.findByPublishYear(year);
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Books published in " + year + " not found");
        }
    }

    //get book by isbn
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ISBN " + isbn + " not found");
        }
    }

    //get list of available books
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableBooks() {
        List<Book> books = bookService.findAvailableBooks();
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No available books found");
        }
    }

    //get list of unavailable books
    @GetMapping("/unavailable")
    public ResponseEntity<?> getUnavailableBooks() {
        List<Book> books = bookService.findUnavailableBooks();
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No unavailable books found");
        }
    }

    //get list of books by author
    @GetMapping("/author/{authorId}")
    public ResponseEntity<?> getBooksByAuthorId(@PathVariable Long authorId) {
        List<Book> books = bookService.findByAuthorId(authorId);
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Books by author with ID " + authorId + " not found");
        }
    }

    //add book
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
        Book addedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }

    //edit book
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        Book editedBook = bookService.editBook(id, updatedBook);
        if (editedBook != null) {
            return ResponseEntity.ok(editedBook);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found");
        }
    }

    //delete book
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.ok("Book deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found");
        }
    }
}