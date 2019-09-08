package br.com.danilosales.stling.dto;

import java.math.BigDecimal;

public class PedidoProdutoDTO {

	private Integer produtoId;
	
	private String produtoDescricao;
	
	private Integer quantidade;
	
	private BigDecimal produtoValor;

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public String getProdutoDescricao() {
		return produtoDescricao;
	}

	public void setProdutoDescricao(String produtoDescricao) {
		this.produtoDescricao = produtoDescricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getProdutoValor() {
		return produtoValor;
	}

	public void setProdutoValor(BigDecimal produtoValor) {
		this.produtoValor = produtoValor;
	}
	
}
