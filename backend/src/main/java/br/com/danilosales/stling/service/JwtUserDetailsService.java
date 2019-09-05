package br.com.danilosales.stling.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.danilosales.stling.model.Usuario;
import br.com.danilosales.stling.repository.UsuarioRepository;
import br.com.danilosales.stling.security.exception.AuthenticationException;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> usuarioExiste = usuarioRepository.findByEmail(username);

		if (!usuarioExiste.isPresent()) {
			throw new UsernameNotFoundException(
					String.format("Usuário não encontrado '%s'.", new Object[] { username }));
		}
		Usuario usuario = usuarioExiste.get();
		
		if(!usuario.getTipo().getDescricao().equals("Vendedor")) {
			throw new AuthenticationException("Usuário não tem permissão de acesso");
		}

		return new User(usuario.getEmail(), usuario.getSenha(), getAuthorities(usuario));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Usuario usuario) {
		return Arrays.asList(new SimpleGrantedAuthority(usuario.getTipo().getDescricao()));
	}
	
}
