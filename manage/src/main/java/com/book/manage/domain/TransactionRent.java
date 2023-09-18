package com.book.manage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION_RENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_RENT_NO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER", referencedColumnName = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOOK", referencedColumnName = "ISBN")
    private Book book;

    @Column(name = "RENT_DATETIME")
    private LocalDateTime datetime;
}
