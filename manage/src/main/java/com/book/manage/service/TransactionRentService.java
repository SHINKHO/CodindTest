package com.book.manage.service;

import com.book.manage.domain.Book;
import com.book.manage.domain.Member;
import com.book.manage.domain.TransactionRent;
import com.book.manage.repository.TransactionRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionRentService {
    @Autowired
    private final TransactionRentRepository rentRepository;

    @Transactional
    public TransactionRent addRent(TransactionRent rent) {
        try {
            return rentRepository.save(rent);
        } catch (Exception e) {
            log.error("Error occurred while adding rent: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while adding rent", e);
        }
    }

    @Transactional(readOnly = true)
    public List<TransactionRent> getAllRents() {
        try {
            return rentRepository.findAll();
        } catch (Exception e) {
            log.error("Error occurred while fetching all rents: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching all rents", e);
        }
    }

    @Transactional(readOnly = true)
    public TransactionRent getRent(Long id) {
        try {
            Optional<TransactionRent> rentOpt = rentRepository.findById(id);
            return rentOpt.orElse(null);
        } catch (Exception e) {
            log.error("Error occurred while fetching rent by ID: {}", id, e);
            throw new RuntimeException("Error occurred while fetching rent by ID: " + id, e);
        }
    }

    @Transactional(readOnly = true)
    public List<TransactionRent> getRentsByMember(Member member) {
        try {
            return rentRepository.findByMember(member);
        } catch (Exception e) {
            log.error("Error occurred while fetching rents by member: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching rents by member", e);
        }
    }

    @Transactional(readOnly = true)
    public List<TransactionRent> getRentsByBook(Book book) {
        try {
            return rentRepository.findByBook(book);
        } catch (Exception e) {
            log.error("Error occurred while fetching rents by book: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while fetching rents by book", e);
        }
    }

    @Transactional
    public void removeRent(TransactionRent rent) {
        try {
            rentRepository.delete(rent);
        } catch (Exception e) {
            log.error("Error occurred while removing rent: {}", e.getMessage(), e);
            throw new RuntimeException("Error occurred while removing rent", e);
        }
    }

    @Transactional
    public void removeRentById(Long id) {
        try {
            rentRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error occurred while removing rent by ID: {}", id, e);
            throw new RuntimeException("Error occurred while removing rent by ID: " + id, e);
        }
    }
}
