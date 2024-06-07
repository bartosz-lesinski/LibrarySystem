package pl.wsb.lesinskibartosz.LibrarySystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Book;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.orElse(null);
    }

    public Book findByTitle(String title) {
        Optional<Book> bookOptional = bookRepository.findByTitle(title);
        return bookOptional.orElse(null);
    }

    public List<Book> findByPublishYear(int year) {
        return bookRepository.findByPublishYear(year);
    }

    public Book findByIsbn(String isbn) {
        Optional<Book> bookOptional = bookRepository.findByIsbn(isbn);
        return bookOptional.orElse(null);
    }

    public List<Book> findAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }

    public List<Book> findUnavailableBooks() {
        return bookRepository.findByAvailable(false);
    }

    public List<Book> findByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book editBook(Long id, Book updatedBook) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublishYear(updatedBook.getPublishYear());
            existingBook.setIsbn(updatedBook.getIsbn());
            existingBook.setAvailable(updatedBook.isAvailable());
            return bookRepository.save(existingBook);
        } else {
            return null;
        }
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
