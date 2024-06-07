package pl.wsb.lesinskibartosz.LibrarySystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Book;
import pl.wsb.lesinskibartosz.LibrarySystem.model.LibraryUser;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Transaction;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.BookRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.LibraryUserRepository;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {


    private final BookRepository bookRepository;
    private final LibraryUserRepository libraryUserRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(BookRepository bookRepository, LibraryUserRepository libraryUserRepository, TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.libraryUserRepository = libraryUserRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> findByBookId(Long bookId) {
        return transactionRepository.findByBookId(bookId);
    }

    @Transactional
    public Transaction borrowBook(Long userId, Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<LibraryUser> userOptional = libraryUserRepository.findById(userId);

        if (!bookOptional.isPresent()) {
            throw new IllegalArgumentException("Book not found");
        }
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("User not found");
        }
        Book book = bookOptional.get();
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is already borrowed");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Transaction transaction = new Transaction();
        transaction.setBookId(bookId);
        transaction.setUserId(userId);
        transaction.setTransactionDate(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        transaction.setReturnDate(calendar.getTime());

        transaction.setFineAmount(BigDecimal.ZERO);
        transactionRepository.save(transaction);
        return transaction;
    }

    public boolean bookReturn(Long transactionId) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);

        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transaction.setPaid(true);
            transactionRepository.save(transaction);

            Long bookId = transaction.getBookId();
            Optional<Book> bookOptional = bookRepository.findById(bookId);

            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                book.setAvailable(true);
                bookRepository.save(book);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteTransaction(Long transactionId) {
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.deleteById(transactionId);
            return true;
        } else {
            return false;
        }
    }
}

