package br.com.danilosales.stling.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

	private Integer id;
	
	private Integer vendedorId;
	
	private String vendedorNome;
	
	private Integer clienteId;
	
	private String clienteNome;
	
	private LocalDateTime dtCadastro;
	
	private boolean pedidoEmitido;
	
	private List<PedidoProdutoDTO> itens = new ArrayList<PedidoProdutoDTO>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVendedorId() {
		return vendedorId;
	}

	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}

	public String getVendedorNome() {
		return vendedorNome;
	}

	public void setVendedorNome(String vendedorNome) {
		this.vendedorNome = vendedorNome;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public boolean isPedidoEmitido() {
		return pedidoEmitido;
	}

	public void setPedidoEmitido(boolean pedidoEmitido) {
		this.pedidoEmitido = pedidoEmitido;
	}

	public List<PedidoProdutoDTO> getItens() {
		return itens;
	}

	public void setItens(List<PedidoProdutoDTO> itens) {
		this.itens = itens;
	}
	
}
