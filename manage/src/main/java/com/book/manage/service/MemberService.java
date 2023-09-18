package com.book.manage.service;

import com.book.manage.domain.Member;
import com.book.manage.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public void addMember(Member member){
        try{
            memberRepository.save(member);
        }catch(Exception e){
            log.error("Error occurred while adding member: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while adding member", e);
        }
    }

    @Transactional
    public void modifyMember(String id, String firstName, String lastName){
        try{
            Optional<Member> memberOpt = memberRepository.findById(id);
            Member member = memberOpt.orElse(null);
            // Modify member properties here
        }catch(Exception e){
            log.error("Error occurred while modifying member: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while modifying member", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Member> getAllMember(){
        try{
            return memberRepository.findAll();
        }catch(Exception e){
            log.error("Error occurred while fetching all members: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching all members", e);
        }
    }

    @Transactional(readOnly = true)
    public Member getMemberById(String id){
        try{
            Optional<Member> memberOpt = memberRepository.findById(id);
            return memberOpt.orElse(null);
        }catch(Exception e){
            log.error("Error occurred while fetching member by ID: {}", id, e);
            throw new RuntimeException("Error occurred while fetching member by ID: " + id, e);
        }
    }

    @Transactional(readOnly = true)
    public List<Member> getMemberByFirstName(String firstName){
        try{
            return memberRepository.findByFirstName(firstName);
        }catch(Exception e){
            log.error("Error occurred while fetching members by first name: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching members by first name", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Member> getMemberByLastName(String lastName){
        try{
            return memberRepository.findByLastName(lastName);
        }catch(Exception e){
            log.error("Error occurred while fetching members by last name: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching members by last name", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Member> getMemberContains(String name){
        try{
            List<Member> unfilteredMember = getAllMember();
            List<Member> members = new LinkedList<>();
            for(Member member: unfilteredMember){
                if(member.getFirstName().contains(name) || member.getLastName().contains(name)){
                    members.add(member);
                }
            }
            return members;
        }catch (Exception e){
            log.error("Error occurred while fetching members containing name: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching members containing name", e);
        }
    }

    @Transactional
    public void removeMember(Member member){
        try{
            memberRepository.delete(member);
        }catch(Exception e){
            log.error("Error occurred while removing member: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while removing member", e);
        }
    }
    @Transactional
    public void removeMemberById(String id){
        try{
            memberRepository.deleteById(id);
        }catch(Exception e){
            log.error("Error occurred while removing member by ID: {}", id, e);
            throw new RuntimeException("Error occurred while removing member by ID: " + id, e);
        }
    }
}
