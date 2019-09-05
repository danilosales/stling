package br.com.danilosales.stling.dto;

import java.io.Serializable;
import java.util.Map;

public class JwtTokenResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final int status;
	
	private final String message;
	
	private final Map<String, String> result;

	public JwtTokenResponse(int status, String message, Map<String, String> result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}
	
	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Map<String, String> getResult() {
		return result;
	}

	

}
