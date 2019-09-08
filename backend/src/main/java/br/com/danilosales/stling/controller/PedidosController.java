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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.danilosales.stling.dto.PedidoDTO;
import br.com.danilosales.stling.dto.PedidoResumoDTO;
import br.com.danilosales.stling.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Pedidos")
@RestController
@RequestMapping({ "/api/pedidos" })
@CrossOrigin(origins = { "http://localhost:4200", "http://stling-frontend:4200" })
public class PedidosController {

	@Autowired
	private PedidoService pedidoService;
	
	@ApiOperation("Busca um pedido pelo nome do vendedor ou nome do cliente")
	@GetMapping
	public List<PedidoResumoDTO> buscarPedidos(@RequestParam("q")String query) {
		return pedidoService.buscarPedidos(query);
	}
	
	@ApiOperation("Cadastrar um novo pedido")
	@PostMapping
	public ResponseEntity<PedidoDTO> inserirPedido(@Valid @RequestBody PedidoDTO pedido) {
		
		this.pedidoService.salvar(pedido);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(new Object[] { pedido.getId() }).toUri();

		return ResponseEntity.created(location).body(pedido);
	}

	@ApiOperation("Atualizar um pedido na base")
	@PutMapping({ "/{codigo}" })
	public ResponseEntity<PedidoDTO> atualizarPedido(@NotNull @PathVariable("codigo") Integer id,
			@Valid @RequestBody PedidoDTO pedido) {
		
		pedido.setId(id);
		this.pedidoService.salvar(pedido);

		return ResponseEntity.ok(pedido);
	}

	@ApiOperation("Excluir um pedido da base")
	@DeleteMapping({ "/{codigo}" })
	public ResponseEntity<?> excluirPedidoPorId(@NotNull @PathVariable("codigo") Integer id) {
		this.pedidoService.excluir(id);

		return ResponseEntity.ok().build();
	}
	
	@ApiOperation("Buscar um pedido por id")
	@GetMapping({ "/{codigo}" })
	public ResponseEntity<PedidoDTO> getPedidoPorId(@NotNull @PathVariable("codigo") Integer id) {
		
		Optional<PedidoDTO> pedidoExistente = this.pedidoService.buscarPedidoPorId(id);
		
		if(pedidoExistente.isPresent()) {
			return ResponseEntity.ok(pedidoExistente.get());
		}
		
		return ResponseEntity.noContent().build();
		
	}
	
}
