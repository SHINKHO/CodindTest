package com.book.manage.repository;

import com.book.manage.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    public List<Member> findAll();

    public Optional<Member> findById(String id);

    public List<Member> findByFirstName(String firstName);

    public List<Member> findByLastName(String lastName);

    public Member save(Member member);

    public void deleteById(String id);
}
