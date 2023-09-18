package com.book.manage.controller.dto;

import com.book.manage.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {

    private String id;
    private String firstName;
    private String lastName;

    public MemberDTO(Member member){
       this.id = member.getId();
       this.firstName = member.getFirstName();
       this.lastName = member.getLastName();
    }
}
