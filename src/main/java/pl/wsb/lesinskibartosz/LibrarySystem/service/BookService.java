package pl.wsb.lesinskibartosz.LibrarySystem.service;

import org.springframework.stereotype.Service;
import pl.wsb.lesinskibartosz.LibrarySystem.Repository.BookRepository;


@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
