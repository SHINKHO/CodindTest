package com.book.manage.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MEMBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "MEMBER_ID", length = 15)
    private String id;

    @Column(name = "MEMBER_NAME_FIRST", length = 35)
    private String firstName;

    @Column(name = "MEMBER_NAME_LAST", length = 35)
    private String lastName;
}
