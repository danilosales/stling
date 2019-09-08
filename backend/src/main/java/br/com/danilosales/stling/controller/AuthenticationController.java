package br.com.danilosales.stling.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.danilosales.stling.dto.JwtTokenRequest;
import br.com.danilosales.stling.dto.JwtTokenResponse;
import br.com.danilosales.stling.security.JwtTokenUtil;
import br.com.danilosales.stling.security.JwtUserDetails;
import br.com.danilosales.stling.security.exception.AuthenticationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Autenticação")
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://stling-frontend:4200" })
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@ApiOperation("Autenticação do usuário")
	@PostMapping(path = "/auth/login")
	public ResponseEntity<?> autenticar(@Valid @RequestBody JwtTokenRequest tokenRequest)
			throws AuthenticationException {
		
		try {
			
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(tokenRequest.getUsername(), tokenRequest.getPassword()));
		
		} catch (DisabledException e) {
			throw new AuthenticationException("O usuário está desabilitado");
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("Credenciais inválidas");
		}

		JwtUserDetails userDetails = (JwtUserDetails) this.userDetailsService.loadUserByUsername(tokenRequest.getUsername());

		String token = this.jwtTokenUtil.generateToken(userDetails);

		Map<String, String> result = new HashMap<>();
		result.put("token", token);
		result.put("username", userDetails.getUsername());
		result.put("usuarioId", userDetails.getUsuario().getId().toString());
		return ResponseEntity.ok(new JwtTokenResponse(200, "OK", result));
	}

	@ApiOperation("Atualizar token do usuário")
	@GetMapping(path = "/auth/refreshToken" )
	public ResponseEntity<?> refreshToken(HttpServletRequest request) {
		
		String authToken = request.getHeader("Bearer ");
		String token = authToken.substring(7);
		String username = this.jwtTokenUtil.getUsernameFromToken(token);

		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

		if (this.jwtTokenUtil.canTokenBeRefreshed(token)) {
			String refreshToken = this.jwtTokenUtil.refreshToken(token);
			
			Map<String, String> result = new HashMap<>();
			result.put("token", refreshToken);
			result.put("username", userDetails.getUsername());
			return ResponseEntity.ok(new JwtTokenResponse(200, "OK", result));
		}
		
		return ResponseEntity.badRequest().build();
	}
}
