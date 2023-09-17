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

import java.time.Year;

@Getter@Setter
public class BookDTO {

    private Long isbn;

    private String title;

    private Short version;

    private Short count;

    private Year year;

    private KdcCode main;

    private KdcCode div;

    private Author author;

    public BookDTO(Book book){
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.version = book.getVersion();
        this.count = book.getCount();
        this.year = book.getYear();
        this.main = book.getMain();
        this.div = book.getDiv();
        this.author = book.getAuthor();
    }



}
