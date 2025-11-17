package com.bankservice.exception;

@SuppressWarnings("serial")
public class ApiException extends RuntimeException {

	public ApiException(String message) {
        super(message);
    }
	
}
