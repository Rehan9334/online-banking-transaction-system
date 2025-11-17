package com.bankservice.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankservice.dto.TransferRequest;
import com.bankservice.model.Account;
import com.bankservice.model.TransactionRecord;
import com.bankservice.repository.AccountRepository;
import com.bankservice.repository.TransactionRepository;

@Service
public class BankingService {

    private final AccountRepository accountRepo;
    private final TransactionRepository trRepo;

    public BankingService(AccountRepository accountRepo, TransactionRepository trRepo) {
        this.accountRepo = accountRepo;
        this.trRepo = trRepo;
    }

    @Transactional
    public TransactionRecord transfer(TransferRequest req) {

        // 1. Load accounts with FOR UPDATE lock
        Account from = accountRepo.findByAccountNumberForUpdate(req.getFromAccount())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account to = accountRepo.findByAccountNumberForUpdate(req.getToAccount())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        BigDecimal amount = req.getAmount();

        // 2. Check if sender has enough money
        if (from.getBalance().compareTo(amount) < 0) {
            TransactionRecord tr = new TransactionRecord(
                    req.getFromAccount(),
                    req.getToAccount(),
                    amount,
                    "FAILED",
                    "Insufficient balance"
            );
            trRepo.save(tr);
            return tr;
        }

        // 3. Deduct + Add money
        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepo.save(from);
        accountRepo.save(to);

        // 4. Record transaction
        TransactionRecord tr = new TransactionRecord(
                req.getFromAccount(),
                req.getToAccount(),
                amount,
                "SUCCESS",
                "Amount transferred successfully"
        );

        trRepo.save(tr);
        return tr;
    }
}
