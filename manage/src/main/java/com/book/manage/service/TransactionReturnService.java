package com.book.manage.service;

import com.book.manage.domain.TransactionReturn;
import com.book.manage.repository.TransactionReturnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionReturnService {

    private final TransactionReturnRepository transactionReturnRepository;

    @Transactional(readOnly = true)
    public List<TransactionReturn> findAll() {
        try {
            return transactionReturnRepository.findAll();
        } catch (Exception e) {
            log.error("Error occurred while fetching all TransactionReturns", e);
            throw new RuntimeException("Error occurred while fetching all TransactionReturns", e);
        }
    }

    @Transactional(readOnly = true)
    public Optional<TransactionReturn> findById(Long id) {
        try {
            return transactionReturnRepository.findById(id);
        } catch (Exception e) {
            log.error("Error occurred while fetching TransactionReturn by ID: {}", id, e);
            throw new RuntimeException("Error occurred while fetching TransactionReturn by ID: " + id, e);
        }
    }

    @Transactional(readOnly = true)
    public List<TransactionReturn> findByRentalPeriod(Integer rentalPeriod) {
        try {
            return transactionReturnRepository.findByRentalPeriod(rentalPeriod);
        } catch (Exception e) {
            log.error("Error occurred while fetching TransactionReturns by rentalPeriod: {}", rentalPeriod, e);
            throw new RuntimeException("Error occurred while fetching TransactionReturns by rentalPeriod: " + rentalPeriod, e);
        }
    }

    @Transactional
    public TransactionReturn save(TransactionReturn transactionReturn) {
        try {
            return transactionReturnRepository.save(transactionReturn);
        } catch (Exception e) {
            log.error("Error occurred while saving TransactionReturn", e);
            throw new RuntimeException("Error occurred while saving TransactionReturn", e);
        }
    }

    @Transactional
    public void remove(TransactionReturn transactionReturn) {
        try {
            transactionReturnRepository.delete(transactionReturn);
        } catch (Exception e) {
            log.error("Error occurred while deleting TransactionReturn", e);
            throw new RuntimeException("Error occurred while deleting TransactionReturn", e);
        }
    }

    @Transactional
    public void removeById(Long id) {
        try {
            transactionReturnRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error occurred while deleting TransactionReturn by ID: {}", id, e);
            throw new RuntimeException("Error occurred while deleting TransactionReturn by ID: " + id, e);
        }
    }
}
