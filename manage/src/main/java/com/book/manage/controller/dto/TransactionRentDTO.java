package com.book.manage.controller.dto;

import com.book.manage.domain.Book;
import com.book.manage.domain.Member;
import com.book.manage.domain.TransactionRent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class TransactionRentDTO {

    private Long id;
    private Member member;
    private Book book;
    private LocalDateTime datetime;

    public TransactionRentDTO(TransactionRent rent){
        this.id = rent.getId();
        this.member = rent.getMember();
        this.book = rent.getBook();
        this.datetime = rent.getDatetime();
    }

}
