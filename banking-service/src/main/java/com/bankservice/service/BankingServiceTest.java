package com.bankservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bankservice.dto.TransferRequest;
import com.bankservice.model.Account;
import com.bankservice.repository.AccountRepository;
import com.bankservice.repository.TransactionRepository;

class BankingServiceTest {

    @Test
    void testTransferSuccess() {

        AccountRepository accountRepo = Mockito.mock(AccountRepository.class);
        TransactionRepository trRepo = Mockito.mock(TransactionRepository.class);
        KafkaNotificationService kafka = Mockito.mock(KafkaNotificationService.class);

        BankingService bankingService = new BankingService(accountRepo, trRepo, kafka);

        Account from = new Account("A1", "User1", new BigDecimal("1000"));
        Account to = new Account("A2", "User2", new BigDecimal("500"));

        Mockito.when(accountRepo.findByAccountNumberForUpdate("A1"))
               .thenReturn(Optional.of(from));
        Mockito.when(accountRepo.findByAccountNumberForUpdate("A2"))
               .thenReturn(Optional.of(to));

        Mockito.when(trRepo.save(Mockito.any())).thenReturn(null);

        TransferRequest req = new TransferRequest();
        req.setFromAccount("A1");
        req.setToAccount("A2");
        req.setAmount(new BigDecimal("200"));

        bankingService.transfer(req);

        assertEquals("800", from.getBalance().toPlainString());
        assertEquals("700", to.getBalance().toPlainString());
    }
}
