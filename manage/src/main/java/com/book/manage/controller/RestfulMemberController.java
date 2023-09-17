package com.book.manage.controller;

import com.book.manage.domain.Member;
import com.book.manage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestfulMemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMember();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable String id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/members")
    public ResponseEntity<Void> addMember(@RequestBody Member member) {
        memberService.addMember(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable String id, @RequestBody Member member) {
        // Implement update logic if needed
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            memberService.removeMember(member);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
