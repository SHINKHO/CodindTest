package com.book.manage.controller;

import com.book.manage.controller.dto.MemberDTO;
import com.book.manage.domain.Member;
import com.book.manage.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class RestfulMemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<Member> members = memberService.getAllMember();
        List<MemberDTO> memberDTOs = members.stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(memberDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable String id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            MemberDTO memberDTO = new MemberDTO(member);
            return ResponseEntity.ok(memberDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Void> addMember(@RequestBody MemberDTO memberDTO) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setFirstName(memberDTO.getFirstName());
        member.setLastName(memberDTO.getLastName());
        memberService.addMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable String id, @RequestBody MemberDTO updatedMemberDTO) {
        Member existingMember = memberService.getMemberById(id);
        if (existingMember != null) {

            if(updatedMemberDTO.getFirstName() != null) {
                existingMember.setFirstName(updatedMemberDTO.getFirstName());
            }
            if(updatedMemberDTO.getLastName() != null){
                existingMember.setLastName(updatedMemberDTO.getLastName());
            }

            memberService.modifyMember(existingMember.getId(),
                    existingMember.getFirstName(),
                    existingMember.getLastName());

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        Member member = memberService.getMemberById(id);
        if (member != null) {
            memberService.removeMember(member);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
