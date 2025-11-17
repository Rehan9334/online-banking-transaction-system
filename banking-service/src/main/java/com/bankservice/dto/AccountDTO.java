package com.bankservice.dto;

import java.math.BigDecimal;



public class AccountDTO {

	private String accountNumber;
    private String ownerName;
    private BigDecimal balance;
    
    public AccountDTO() {  // Required for JSON and Spring
    }
    
	public AccountDTO(String accountNumber, String ownerName, BigDecimal balance) {
		super();
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
		
		
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
    
    
    
}
