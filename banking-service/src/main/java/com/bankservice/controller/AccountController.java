package com.bankservice.controller;

import com.bankservice.dto.AccountDTO;
import com.bankservice.dto.TransferRequest;
import com.bankservice.model.Account;
import com.bankservice.model.TransactionRecord;
import com.bankservice.repository.AccountRepository;
import com.bankservice.service.BankingService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountRepository accountRepo;
    private final BankingService bankingService;

    public AccountController(AccountRepository accountRepo, BankingService bankingService) {
        this.accountRepo = accountRepo;
        this.bankingService = bankingService;
    }

    // CREATE ACCOUNT
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO dto) {

        Account acc = new Account(
                dto.getAccountNumber(),
                dto.getOwnerName(),
                dto.getBalance() == null ? BigDecimal.ZERO : dto.getBalance()
        );

        accountRepo.save(acc);

        return ResponseEntity.ok(
                new AccountDTO(acc.getAccountNumber(), acc.getOwnerName(), acc.getBalance()));
    }

    // GET ACCOUNT BY NUMBER
    @GetMapping("/{accNo}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable String accNo) {
        Account acc = accountRepo.findByAccountNumber(accNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return ResponseEntity.ok(
                new AccountDTO(acc.getAccountNumber(), acc.getOwnerName(), acc.getBalance()));
    }

    // TRANSFER MONEY
    @PostMapping("/transfer")
    public ResponseEntity<TransactionRecord> transfer(@Valid @RequestBody TransferRequest req) {
        return ResponseEntity.ok(bankingService.transfer(req));
    }
}
