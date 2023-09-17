package com.book.manage.domain;

import jakarta.persistence.*;

@Entity
@Table(name="AUTHOR")
public class Author {

    @Id
    @Column(name="AUTHOR_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="AUTHOR_NAME_FIRST",length=35)
    private String firstName;

    @Column(name="AUTHOR_NAME_LAST",length=35)
    private String lastName;

    public Author() {
    }

    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
