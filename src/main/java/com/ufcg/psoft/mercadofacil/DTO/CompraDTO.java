package com.ufcg.psoft.mercadofacil.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CompraDTO {

	private Long id;
	
	private List<ProdutoCarrinhoDTO> produtos;
	
	private BigDecimal valorTotal;
	
	private LocalDateTime dataCriacao;
	
	public CompraDTO() {}
	
	public CompraDTO(
			List<ProdutoCarrinhoDTO> produtos,
			BigDecimal valorTotal,
			LocalDateTime dataCriacao) {
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
	}
	
	public CompraDTO(
			Long id,
			List<ProdutoCarrinhoDTO> produtos,
			BigDecimal valorTotal,
			LocalDateTime dataCriacao) {
		this.id = id;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
	}

	public List<ProdutoCarrinhoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoCarrinhoDTO> produtos) {
		this.produtos = produtos;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
