package br.com.danilosales.stling.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danilosales.stling.dto.PedidoDTO;
import br.com.danilosales.stling.dto.PedidoResumoDTO;
import br.com.danilosales.stling.model.Pedido;
import br.com.danilosales.stling.model.PedidoProduto;
import br.com.danilosales.stling.repository.PedidoRepository;
import br.com.danilosales.stling.service.exception.EntidadeInexistente;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<PedidoResumoDTO> buscarPedidos(String query) {
		return pedidoRepository.buscarPedidos(query);
	}

	
	public Optional<PedidoDTO> buscarPedidoPorId(Integer id) {
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);
		
		if(!pedido.isPresent()) {
			return Optional.empty();
		}
		
		PedidoDTO pedidoDTO = this.modelMapper.map(pedido.get(), PedidoDTO.class);
		pedidoDTO.setPedidoEmitido(pedido.get().getDtEmissao() != null);
		
		return Optional.of(pedidoDTO);

	}

	@Transactional
	public void excluir(Integer id) {
		Optional<Pedido> cliente = this.pedidoRepository.findById(id);
		
		if(!cliente.isPresent()) {
			throw new EntidadeInexistente("O id informado não existe");
		}
		
		if(cliente.get().getDtEmissao() != null) {
			throw new IllegalArgumentException("Não se pode excluir um pedido já emitido");
		}
		
		this.pedidoRepository.deleteById(id);
		
	}

	@Transactional
	public void salvar(PedidoDTO pedidoDTO) {
		
		Pedido pedido = this.modelMapper.map(pedidoDTO, Pedido.class);
		
		if(pedidoDTO.getId() != null) {
			Optional<Pedido> pedidoExistente = this.pedidoRepository.findById(pedidoDTO.getId());
			
			if(!pedidoExistente.isPresent()) {
				throw new IllegalArgumentException("O id informado no pedido não existe");
			}
			pedido.setDtCadastro(pedidoExistente.get().getDtCadastro());
			pedido.setDtFaturamento(pedidoExistente.get().getDtFaturamento());
			if(pedidoDTO.isPedidoEmitido()) {
				pedido.setDtEmissao(pedidoExistente.get().getDtEmissao() != null ? pedidoExistente.get().getDtEmissao() : LocalDateTime.now());				
			}
			
		} else {
			pedido.setDtCadastro(LocalDateTime.now());
			pedido.setDtEmissao(pedidoDTO.isPedidoEmitido() ? LocalDateTime.now() : null);
		}
		
		pedido.getItens().forEach(i -> i.setPedido(pedido));
		
		this.pedidoRepository.save(pedido);
		this.pedidoRepository.flush();
		
		pedido.setValorTotal(calcularValorTotal(pedido.getItens()));
		
		this.pedidoRepository.save(pedido);
		
		pedidoDTO.setId(pedido.getId());
		
	}


	private BigDecimal calcularValorTotal(List<PedidoProduto> itens) {
		return itens.stream()
				.map(PedidoProduto::getValorTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		
	}
	
}
