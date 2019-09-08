package br.com.danilosales.stling.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.danilosales.stling.dto.JwtTokenResponse;

@RestControllerAdvice
public class IllegalArgumentExceptionHandler {
	
	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<JwtTokenResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		JwtTokenResponse response = new JwtTokenResponse(400, e.getMessage(), null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
