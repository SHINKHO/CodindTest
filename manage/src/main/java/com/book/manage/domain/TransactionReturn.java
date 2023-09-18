package com.book.manage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION_RETURN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_RETURN_NO")
    private Long id;

    @OneToOne
    @JoinColumn(name = "TRANSACTION_RENT_NO", referencedColumnName = "TRANSACTION_RENT_NO", nullable = false)
    private TransactionRent transactionRentNo;

    @Column(name = "RETURN_DATETIME")
    private LocalDateTime datetime;

    @Column(name = "RENTAL_PERIOD")
    private Integer rentalPeriod;
}
