package br.com.danilosales.stling.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.danilosales.stling.model.Cliente;
import br.com.danilosales.stling.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Clientes")
@RestController
@RequestMapping({ "/api/clientes" })
@CrossOrigin(origins = { "http://localhost:4200", "http://stling-frontend:4200" })
public class ClientesController {

	@Autowired
	private ClienteService clienteService;

	@ApiOperation("Retorna uma lista de clientes")
	@GetMapping
	public List<Cliente> buscarTodosOsClientes() {
		return this.clienteService.buscarTodosOsClientes();
	}

	@ApiOperation("Cadastrar um usuário")
	@PostMapping
	public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody Cliente cliente) {
		
		this.clienteService.salvar(cliente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(new Object[] { cliente.getId() }).toUri();

		return ResponseEntity.created(location).body(cliente);
	}

	@ApiOperation("Atualizar um cliente da base")
	@PutMapping({ "/{codigo}" })
	public ResponseEntity<Cliente> atualizarCliente(@NotNull @PathVariable("codigo") Integer id,
			@Valid @RequestBody Cliente cliente) {
		
		cliente.setId(id);
		this.clienteService.salvar(cliente);

		return ResponseEntity.ok(cliente);
	}

	@ApiOperation("Excluir um cliente da base")
	@DeleteMapping({ "/{codigo}" })
	public ResponseEntity<?> excluirClientePorId(@NotNull @PathVariable("codigo") Integer id) {
		this.clienteService.excluir(id);

		return ResponseEntity.ok().build();
	}
	
	@ApiOperation("Buscar um cliente por id")
	@GetMapping({ "/{codigo}" })
	public ResponseEntity<Cliente> getClientePorId(@NotNull @PathVariable("codigo") Integer id) {
		
		Optional<Cliente> clienteExistente = this.clienteService.buscarClientePorId(id);
		
		if(clienteExistente.isPresent()) {
			return ResponseEntity.ok(clienteExistente.get());
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
}
