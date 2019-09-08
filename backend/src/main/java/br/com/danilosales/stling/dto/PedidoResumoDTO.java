package br.com.danilosales.stling.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResumoDTO {

	private Integer id;
	
	private String nomeCliente;
	
	private String nomeVendedor;
	
	private LocalDateTime dataCadastro;
	
	private BigDecimal valorTotal;
	
	private Integer totalItens;
	
	private boolean pedidoFinalizado;
	
	public PedidoResumoDTO(Integer id, String nomeCliente, String nomeVendedor, LocalDateTime dataCadastro,
			BigDecimal valorTotal, Integer totalItens, boolean pedidoFinalizado) {
		this.id = id;
		this.nomeCliente = nomeCliente;
		this.nomeVendedor = nomeVendedor;
		this.dataCadastro = dataCadastro;
		this.valorTotal = valorTotal;
		this.totalItens = totalItens;
		this.pedidoFinalizado = pedidoFinalizado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public boolean isPedidoFinalizado() {
		return pedidoFinalizado;
	}

	public void setPedidoFinalizado(boolean pedidoFinalizado) {
		this.pedidoFinalizado = pedidoFinalizado;
	}

	public Integer getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(Integer totalItens) {
		this.totalItens = totalItens;
	}
	
}
