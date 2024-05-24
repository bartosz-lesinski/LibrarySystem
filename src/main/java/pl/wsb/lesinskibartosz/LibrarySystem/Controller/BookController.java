package pl.wsb.lesinskibartosz.LibrarySystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wsb.lesinskibartosz.LibrarySystem.Repository.BookRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Book;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/test")
    public int test() {
        return 1;
    }
}