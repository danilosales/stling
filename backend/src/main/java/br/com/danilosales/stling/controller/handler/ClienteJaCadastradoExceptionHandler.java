package br.com.danilosales.stling.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.danilosales.stling.dto.JwtTokenResponse;
import br.com.danilosales.stling.service.exception.ClienteJaCadastradoException;

@RestControllerAdvice
public class ClienteJaCadastradoExceptionHandler {
	
	@ExceptionHandler({ ClienteJaCadastradoException.class })
	public ResponseEntity<JwtTokenResponse> handleClienteJaCadastradoException(ClienteJaCadastradoException e) {
		JwtTokenResponse response = new JwtTokenResponse(400, e.getMessage(), null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
