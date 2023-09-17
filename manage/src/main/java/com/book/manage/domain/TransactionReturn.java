package com.book.manage.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="TRANSACTION_RETURN")
public class TransactionReturn {
    @Id
    @Column(name="TRANSACTION_RETURN_NO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "TRANSACTION_RENT_NO",referencedColumnName = "TRANSACTION_RENT_NO",nullable = false)
    private TransactionRent transactionRentNo;

    @Column(name="RETURN_DATETIME")
    private LocalDateTime datetime;

    @Column(name="RENTAL_PERIOD")
    private Integer rentalPeriod;

    public TransactionReturn() {
    }

    public TransactionReturn(Long id, TransactionRent transactionRentNo, LocalDateTime datetime) {
        this.id = id;
        this.transactionRentNo = transactionRentNo;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionRent getTransactionRentNo() {
        return transactionRentNo;
    }

    public void setTransactionRentNo(TransactionRent transactionRentNo) {
        this.transactionRentNo = transactionRentNo;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public Integer getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(Integer rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    @Override
    public String toString() {
        return "TransactionReturn{" +
                "id=" + id +
                ", transactionRentNo=" + transactionRentNo +
                ", datetime=" + datetime +
                ", rentalPeriod=" + rentalPeriod +
                '}';
    }
}
