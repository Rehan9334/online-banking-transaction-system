package com.bankservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bankservice.model.Account;

import jakarta.persistence.LockModeType;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(String accountNumber);

	
	    @Lock(LockModeType.PESSIMISTIC_WRITE)
	    @Query("select a from Account a where a.accountNumber = :accNo")
	Optional<Account> findByAccountNumberForUpdate(@Param("accNo") String accountNumber);
}
