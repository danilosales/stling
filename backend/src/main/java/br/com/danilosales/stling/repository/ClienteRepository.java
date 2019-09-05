package br.com.danilosales.stling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.danilosales.stling.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	public Optional<Cliente> findByCpfCnpj(String cpfCnpj);
	
}
