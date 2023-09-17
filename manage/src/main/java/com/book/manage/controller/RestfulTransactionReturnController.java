package com.book.manage.controller;

import com.book.manage.domain.TransactionReturn;
import com.book.manage.service.TransactionReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RestfulTransactionReturnController {

    private final TransactionReturnService transactionReturnService;

    @GetMapping("/transactionReturns")
    public ResponseEntity<List<TransactionReturn>> getAllTransactionReturns() {
        List<TransactionReturn> transactionReturns = transactionReturnService.findAll();
        return new ResponseEntity<>(transactionReturns, HttpStatus.OK);
    }

    @GetMapping("/transactionReturns/{id}")
    public ResponseEntity<TransactionReturn> getTransactionReturnById(@PathVariable Long id) {
        Optional<TransactionReturn> transactionReturn = transactionReturnService.findById(id);
        if (transactionReturn.isPresent()) {
            return new ResponseEntity<>(transactionReturn.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transactionReturns")
    public ResponseEntity<Void> addTransactionReturn(@RequestBody TransactionReturn transactionReturn) {
        transactionReturnService.save(transactionReturn);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/transactionReturns/{id}")
    public ResponseEntity<Void> deleteTransactionReturn(@PathVariable Long id) {
        Optional<TransactionReturn> transactionReturn = transactionReturnService.findById(id);
        if (transactionReturn.isPresent()) {
            transactionReturnService.remove(transactionReturn.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
