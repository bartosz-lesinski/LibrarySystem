package pl.wsb.lesinskibartosz.LibrarySystem.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Transaction;
import pl.wsb.lesinskibartosz.LibrarySystem.service.TransactionService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //get all transactions
    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        return ResponseEntity.ok(transactions);
    }

    //get all transactions by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.findByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    //get all transactions by Book ID
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Transaction>> getTransactionsByBookId(@PathVariable Long bookId) {
        List<Transaction> transactions = transactionService.findByBookId(bookId);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/borrow/{userId}/{bookId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long userId, @PathVariable Long bookId) {
        try {
            Transaction transaction = transactionService.borrowBook(userId, bookId);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/return/{transactionId}")
    public ResponseEntity<?> returnBook(@PathVariable Long transactionId) {
        boolean returned = transactionService.bookReturn(transactionId);
        if (returned) {
            return ResponseEntity.ok("Book returned successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        boolean deleted = transactionService.deleteTransaction(id);
        if (deleted) {
            return ResponseEntity.ok("Transaction deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction with ID " + id + " not found");
        }
    }
}