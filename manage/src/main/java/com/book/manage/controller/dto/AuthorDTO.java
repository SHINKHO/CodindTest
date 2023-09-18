package com.book.manage.controller.dto;

import com.book.manage.domain.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public AuthorDTO(Author author){
        this.id= author.getId();
        this.firstName=author.getFirstName();
        this.lastName=author.getLastName();
    }
}
