package com.ufcg.psoft.mercadofacil.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CompraHistoricoDTO {
	
	private Long id;
	
	private Long usuarioId;
	
	private List<ProdutoCarrinhoDTO> produtos;
	
	private BigDecimal valorTotal;
	
	private LocalDateTime dataCriacao;
	
	public CompraHistoricoDTO() {}
	
	public CompraHistoricoDTO(
			List<ProdutoCarrinhoDTO> produtos,
			BigDecimal valorTotal,
			LocalDateTime dataCriacao,
			Long usuarioId) {
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.usuarioId = usuarioId;
	}
	
	public CompraHistoricoDTO(
			Long id,
			List<ProdutoCarrinhoDTO> produtos,
			BigDecimal valorTotal,
			LocalDateTime dataCriacao,
			Long usuarioId) {
		this.id = id;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.usuarioId = usuarioId;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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
