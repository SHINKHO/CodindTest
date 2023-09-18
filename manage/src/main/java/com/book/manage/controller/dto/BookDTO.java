package com.book.manage.controller.dto;

import com.book.manage.domain.Author;
import com.book.manage.domain.Book;
import com.book.manage.domain.KdcCode;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class BookDTO {

    private String isbn;

    private String title;

    private Short bookVersion;

    private Short bookCount;

    private LocalDate writtenDate;

    private KdcCode kdcCode;

    private Author author;

    public BookDTO(Book book){
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.bookVersion = book.getBookVersion();
        this.bookCount = book.getBookCount();
        this.writtenDate = book.getWrittenDate();
        this.kdcCode = book.getKdcCode();
        this.author = book.getAuthor();
    }



}
