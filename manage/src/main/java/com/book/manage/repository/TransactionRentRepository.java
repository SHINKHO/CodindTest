package com.book.manage.repository;

import com.book.manage.domain.Book;
import com.book.manage.domain.Member;
import com.book.manage.domain.TransactionRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRentRepository extends JpaRepository<TransactionRent, Long> {

    public List<TransactionRent> findAll();

    public Optional<TransactionRent> findById(Long id);

    public List<TransactionRent> findByMember(Member member);

    public List<TransactionRent> findByBook(Book book);

    public TransactionRent save(TransactionRent transactionRent);

    public void delete(TransactionRent transactionRent);

    public void deleteById(Long id);
}