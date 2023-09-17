package com.book.manage.controller;

import com.book.manage.domain.TransactionRent;
import com.book.manage.service.TransactionRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestfulTransactionRentController {

    private final TransactionRentService transactionRentService;

    @GetMapping("/transactionRents")
    public ResponseEntity<List<TransactionRent>> getAllTransactionRents() {
        List<TransactionRent> transactionRents = transactionRentService.getAllRents();
        return new ResponseEntity<>(transactionRents, HttpStatus.OK);
    }

    @GetMapping("/transactionRents/{id}")
    public ResponseEntity<TransactionRent> getTransactionRentById(@PathVariable Long id) {
        TransactionRent transactionRent = transactionRentService.getRent(id);
        if (transactionRent != null) {
            return new ResponseEntity<>(transactionRent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transactionRents")
    public ResponseEntity<Void> addTransactionRent(@RequestBody TransactionRent transactionRent) {
        transactionRentService.addRent(transactionRent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/transactionRents/{id}")
    public ResponseEntity<Void> deleteTransactionRent(@PathVariable Long id) {
        TransactionRent transactionRent = transactionRentService.getRent(id);
        if (transactionRent != null) {
            transactionRentService.removeRent(transactionRent);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
