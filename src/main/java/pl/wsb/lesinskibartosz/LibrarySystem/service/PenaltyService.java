package pl.wsb.lesinskibartosz.LibrarySystem.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.wsb.lesinskibartosz.LibrarySystem.model.Transaction;
import pl.wsb.lesinskibartosz.LibrarySystem.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PenaltyService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public PenaltyService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void checkAndApplyPenalties() {
        applyPenalties();
    }

    @PostConstruct
    public void init() {
        applyPenalties();
    }

    private void applyPenalties() {
        List<Transaction> transactions = transactionRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Transaction transaction : transactions) {
            if (transaction.getReturnDate() != null) {
                LocalDate returnDate = transaction.getReturnDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long daysOverdue = ChronoUnit.DAYS.between(returnDate, today);

                if (daysOverdue > 0) {
                    BigDecimal penalty = calculatePenalty(daysOverdue);
                    transaction.setFineAmount(penalty);
                    transactionRepository.save(transaction);
                }
            }
        }
    }

    private BigDecimal calculatePenalty(long daysOverdue) {
        return BigDecimal.valueOf(daysOverdue).multiply(BigDecimal.valueOf(0.10));
    }
}

