package com.bankservice.controller;

import com.bankservice.dto.TransactionDTO;
import com.bankservice.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    // CREATE TRANSACTION
    @PostMapping
    public ResponseEntity<TransactionDTO> create(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount) {

        return ResponseEntity.ok(service.createTransaction(from, to, amount));
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // GET ALL TRANSACTIONS FOR ACCOUNT
    @GetMapping("/account/{accNo}")
    public ResponseEntity<List<TransactionDTO>> getForAccount(@PathVariable String accNo) {
        return ResponseEntity.ok(service.getForAccount(accNo));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
