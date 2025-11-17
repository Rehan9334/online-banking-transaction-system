package com.bankservice.service;

import com.bankservice.dto.TransactionDTO;
import com.bankservice.model.TransactionRecord;
import com.bankservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    // Convert Entity to DTO
    private TransactionDTO mapToDTO(TransactionRecord tr) {
        return new TransactionDTO(
                tr.getId(),
                tr.getFromAccount(),
                tr.getToAccount(),
                tr.getAmount(),
                tr.getStatus(),
                tr.getMessage(),
                tr.getTimestamp()
        );
    }

    // CREATE a new manual transaction
    public TransactionDTO createTransaction(String from, String to, BigDecimal amount) {

        TransactionRecord tr = new TransactionRecord(
                from,
                to,
                amount,
                "SUCCESS",
                "Created manually"
        );

        repo.save(tr);
        return mapToDTO(tr);
    }

    // SAVE (used by transfer service also)
    public TransactionDTO save(TransactionRecord tr) {
        repo.save(tr);
        return mapToDTO(tr);
    }

    // GET ALL transactions
    public List<TransactionDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // GET transaction by ID
    public TransactionDTO getById(Long id) {
        TransactionRecord tr = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return mapToDTO(tr);
    }

    // GET transactions for a specific account
    public List<TransactionDTO> getForAccount(String accNo) {
        return repo.findByFromAccountOrToAccountOrderByTimestampDesc(accNo, accNo)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // DELETE transaction
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
