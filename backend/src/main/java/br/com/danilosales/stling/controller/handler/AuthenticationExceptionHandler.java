package br.com.danilosales.stling.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.danilosales.stling.dto.JwtTokenResponse;
import br.com.danilosales.stling.security.exception.AuthenticationException;

@RestControllerAdvice
public class AuthenticationExceptionHandler {
	
	@ExceptionHandler({ AuthenticationException.class, UsernameNotFoundException.class })
	public ResponseEntity<JwtTokenResponse> handleAuthenticationException(AuthenticationException e) {
		JwtTokenResponse response = new JwtTokenResponse(401, e.getMessage(), null);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
}
