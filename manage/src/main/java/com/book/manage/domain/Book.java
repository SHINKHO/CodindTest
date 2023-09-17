package com.book.manage.domain;

import jakarta.persistence.*;

import java.time.Year;

@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @Column(name = "ISBN")
    private Long isbn;

    @Column(name="TITLE" ,length = 40)
    private String title;
    @Column(name="BOOK_VER")
    private Short version;
    @Column(name="BOOK_CNT")
    private Short count;
    @Column(name="WRITTEN_YEAR")
    private Year year;
    //fks
    @ManyToOne
    @JoinColumn(name = "KDC_MAIN",referencedColumnName="KDC_MAIN", nullable = false)
    private KdcCode main;

    @ManyToOne
    @JoinColumn(name = "KDC_DIV",referencedColumnName="KDC_DIV", nullable = false)
    private KdcCode div;

    @ManyToOne
    @JoinColumn(name="AUTHOR",referencedColumnName= "AUTHOR_ID",nullable = false)
    private Author author;

    public Book(){
    }
    public Book(Long isbn, String title, Short version, Short count, Year year, KdcCode main, KdcCode div, Author author) {
        this.isbn = isbn;
        this.title = title;
        this.version = version;
        this.count = count;
        this.year = year;
        this.main = main;
        this.div = div;
        this.author = author;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Short getCount() {
        return count;
    }

    public void setCount(Short count) {
        this.count = count;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public KdcCode getMain() {
        return main;
    }

    public void setMain(KdcCode main) {
        this.main = main;
    }

    public KdcCode getDiv() {
        return div;
    }

    public void setDiv(KdcCode div) {
        this.div = div;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", version=" + version +
                ", count=" + count +
                ", year=" + year +
                ", main=" + main +
                ", div=" + div +
                ", author=" + author +
                '}';
    }
}
