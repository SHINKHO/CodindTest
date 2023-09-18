package com.book.manage.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AUTHOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUTHOR_ID")
    private Long id;

    @Column(name = "AUTHOR_NAME_FIRST", length = 35)
    private String firstName;

    @Column(name = "AUTHOR_NAME_LAST", length = 35)
    private String lastName;
}
