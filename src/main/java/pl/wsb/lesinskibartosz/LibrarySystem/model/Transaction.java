package pl.wsb.lesinskibartosz.LibrarySystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "fine_amount")
    private BigDecimal fineAmount;


    @Column(name = "paid", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean paid = false;


}
