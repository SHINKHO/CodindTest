package com.book.manage.controller.dto;

import com.book.manage.domain.TransactionRent;
import com.book.manage.domain.TransactionReturn;
import lombok.*;

import java.time.LocalDateTime;
@Getter @Setter
public class TransactionReturnDTO {
        private Long id;
        private TransactionRent transactionRentNo;
        private LocalDateTime datetime;
        private Integer rentalPeriod;

        public TransactionReturnDTO(TransactionReturn transactionReturn){
            this.id = transactionReturn.getId();
            this.transactionRentNo = transactionReturn.getTransactionRentNo();
            this.datetime = transactionReturn.getDatetime();
            this.rentalPeriod = transactionReturn.getRentalPeriod();;
        }
}
