package com.book.manage.controller;

import com.book.manage.controller.dto.TransactionReturnDTO;
import com.book.manage.domain.TransactionReturn;
import com.book.manage.service.TransactionReturnService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactionReturns")
public class RestfulTransactionReturnController {
    private final TransactionReturnService transactionReturnService;

    @GetMapping("/")
    public ResponseEntity<List<TransactionReturnDTO>> getAllTransactionReturns() {
        List<TransactionReturn> transactionReturns = transactionReturnService.findAll();
        List<TransactionReturnDTO> transactionReturnDTOs = new LinkedList<>();
        for (TransactionReturn transactionReturn : transactionReturns) {
            transactionReturnDTOs.add(new TransactionReturnDTO(transactionReturn));
        }
        return ResponseEntity.ok(transactionReturnDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionReturnDTO> getTransactionReturnById(@PathVariable Long id) {
        TransactionReturn transactionReturn = transactionReturnService.findById(id).orElse(null);
        if (transactionReturn != null) {
            return ResponseEntity.ok(new TransactionReturnDTO(transactionReturn));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Void> addTransactionReturn(@RequestBody TransactionReturnDTO transactionReturnDTO) {
        if (transactionReturnDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        TransactionReturn transactionReturn = new TransactionReturn(
                transactionReturnDTO.getId(),
                transactionReturnDTO.getTransactionRentNo(),
                transactionReturnDTO.getDatetime(),
                transactionReturnDTO.getRentalPeriod()
        );
        transactionReturnService.save(transactionReturn);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTransactionReturn(@PathVariable Long id, @RequestBody TransactionReturnDTO updatedTransactionReturnDTO) {
        TransactionReturn existingTransactionReturn = transactionReturnService.findById(id).orElse(null);
        if (existingTransactionReturn == null) {
            return ResponseEntity.notFound().build();
        }
        if (updatedTransactionReturnDTO.getDatetime() != null) {
            existingTransactionReturn.setDatetime(updatedTransactionReturnDTO.getDatetime());
        }

        if (updatedTransactionReturnDTO.getRentalPeriod() != null) {
            existingTransactionReturn.setRentalPeriod(updatedTransactionReturnDTO.getRentalPeriod());
        }
        transactionReturnService.save(existingTransactionReturn);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionReturn(@PathVariable Long id) {
        TransactionReturn transactionReturn = transactionReturnService.findById(id).orElse(null);
        if (transactionReturn != null) {
            transactionReturnService.remove(transactionReturn);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
