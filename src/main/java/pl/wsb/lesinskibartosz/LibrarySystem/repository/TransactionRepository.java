package pl.wsb.lesinskibartosz.LibrarySystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByBookId(Long bookId);

    List<Transaction> findAllByReturnDateBeforeAndPaidIsFalse(LocalDate now);
}
