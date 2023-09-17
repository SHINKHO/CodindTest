package com.book.manage.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="TRANSACTION_RENT")
public class TransactionRent {
    @Id
    @Column(name = "TRANSACTION_RENT_NO")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="MEMBER",referencedColumnName="MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="BOOK",referencedColumnName="isbn")
    private Book book;

    @Column(name="RENT_DATETIME")
    private LocalDateTime datetime;

    public TransactionRent() {
    }

    public TransactionRent(Long id, Member member, Book book, LocalDateTime datetime) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "TransactionRent{" +
                "id=" + id +
                ", member=" + member +
                ", book=" + book +
                ", datetime=" + datetime +
                '}';
    }
}
