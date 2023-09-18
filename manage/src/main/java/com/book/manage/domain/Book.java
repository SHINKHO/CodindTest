package com.book.manage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "BOOK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name = "ISBN", length = 13)
    private String isbn;

    @Column(name = "TITLE", length = 40)
    private String title;

    @Column(name = "BOOK_CNT")
    private Short bookCount;

    @Column(name = "BOOK_VER")
    private Short bookVersion;

    @Column(name = "WRITTEN_DATE")
    private LocalDate writtenDate;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "KDC_MAIN", referencedColumnName = "KDC_MAIN"),
            @JoinColumn(name = "KDC_DIV", referencedColumnName = "KDC_DIV")
    })
    private KdcCode kdcCode;

    @ManyToOne
    @JoinColumn(name = "AUTHOR", referencedColumnName = "AUTHOR_ID")
    private Author author;
}
