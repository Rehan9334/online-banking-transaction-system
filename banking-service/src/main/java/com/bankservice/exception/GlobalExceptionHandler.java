package com.bankservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<?> handleException(ApiException ex){
		Map<String, Object> body=new HashMap();
		body.put("error", ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(err ->
        errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAll(Exception ex) {
        Map<String,Object> body = new HashMap<>();
        body.put("error", "Internal Server Error");
        body.put("details", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
	
}
