package com.book.manage.controller;

import com.book.manage.controller.dto.TransactionRentDTO;
import com.book.manage.domain.TransactionRent;
import com.book.manage.service.TransactionRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactionRents")
public class RestfulTransactionRentController {

    private final TransactionRentService transactionRentService;

    @GetMapping("/")
    public ResponseEntity<List<TransactionRentDTO>> getAllTransactionRents() {
        List<TransactionRent> transactionRents = transactionRentService.getAllRents();
        List<TransactionRentDTO> transactionRentDTOs = new LinkedList<>();

        for(TransactionRent rent : transactionRents){
            transactionRentDTOs.add(new TransactionRentDTO(rent));
        }
        return ResponseEntity.ok(transactionRentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionRentDTO> getTransactionRentById(@PathVariable Long id) {
        TransactionRent transactionRent = transactionRentService.getRent(id);
        if (transactionRent != null) {
            return ResponseEntity.ok(new TransactionRentDTO(transactionRent));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<Void> addTransactionRent(@RequestBody TransactionRentDTO transactionRentDTO) {
        if (transactionRentDTO != null) {
            TransactionRent transactionRent = new TransactionRent(
                    transactionRentDTO.getId(),
                    transactionRentDTO.getMember(),
                    transactionRentDTO.getBook(),
                    transactionRentDTO.getDatetime()
            );
            transactionRentService.addRent(transactionRent);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @DeleteMapping("/{id}")
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
