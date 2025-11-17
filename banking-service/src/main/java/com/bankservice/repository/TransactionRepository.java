package com.bankservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankservice.model.TransactionRecord;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionRecord, Long> {

	 List<TransactionRecord> findByFromAccount(String accNo);

	 List<TransactionRecord> findByToAccount(String accNo);
	    
	 List<TransactionRecord> findByFromAccountOrToAccountOrderByTimestampDesc(String from, String to);   
}
