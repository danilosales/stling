package br.com.danilosales.stling.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danilosales.stling.model.Cliente;
import br.com.danilosales.stling.repository.ClienteRepository;
import br.com.danilosales.stling.service.exception.ClienteJaCadastradoException;
import br.com.danilosales.stling.service.exception.EntidadeInexistente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteExistente = this.clienteRepository.findByCpfCnpj(cliente.getCpfCnpj());
		
		if(clienteExistente.isPresent() && cliente.getId() == null) {
			throw new ClienteJaCadastradoException("CPF/CNPJ já existe na base de dados");
		}
		
		this.clienteRepository.save(cliente);
	}
	
	public List<Cliente> buscarTodosOsClientes() {
		return this.clienteRepository.findAll();
	}
	
	@Transactional
	public void excluir(Integer id) {
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		
		if(!cliente.isPresent()) {
			throw new EntidadeInexistente("O id informado não existe");
		}
		
		this.clienteRepository.deleteById(id);
	}
	
	public Optional<Cliente> buscarClientePorId(Integer id) {
		return this.clienteRepository.findById(id);
	}
	
	
}
