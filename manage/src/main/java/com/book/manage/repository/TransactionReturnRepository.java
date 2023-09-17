package com.book.manage.repository;

import com.book.manage.domain.TransactionReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionReturnRepository extends JpaRepository<TransactionReturn, Long> {

    public List<TransactionReturn> findAll();

    public Optional<TransactionReturn> findById(Long id);

    public List<TransactionReturn> findByRentalPeriod(Integer rentalPeriod);

    public TransactionReturn save(TransactionReturn transactionReturn);

    public void delete(TransactionReturn transactionReturn);

    public void deleteById(Long id);
}
