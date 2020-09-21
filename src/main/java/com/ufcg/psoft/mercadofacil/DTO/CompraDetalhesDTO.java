package com.ufcg.psoft.mercadofacil.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CompraDetalhesDTO {

	private Long id;
	
	private List<ProdutoCarrinhoDTO> produtos;
	
	private BigDecimal valorTotal;
	
	private LocalDateTime dataCriacao;
	
	private InfoPagamentoDetalhesDTO pagamento;
	
	public CompraDetalhesDTO() {}
	
	public CompraDetalhesDTO(
			List<ProdutoCarrinhoDTO> produtos,
			BigDecimal valorTotal,
			LocalDateTime dataCriacao,
			InfoPagamentoDetalhesDTO infoPagamentoDetalhesDTO) {
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.pagamento = infoPagamentoDetalhesDTO;
	}
	
	public CompraDetalhesDTO(
			Long id,
			List<ProdutoCarrinhoDTO> produtos,
			BigDecimal valorTotal,
			LocalDateTime dataCriacao,
			InfoPagamentoDetalhesDTO infoPagamentoDetalhesDTO) {
		this.id = id;
		this.produtos = produtos;
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.pagamento = infoPagamentoDetalhesDTO;
	}

	public List<ProdutoCarrinhoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoCarrinhoDTO> produtos) {
		this.produtos = produtos;
	}

	public void setId(Long id) {
		this.id = id;
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

	public InfoPagamentoDetalhesDTO getPagamento() {
		return pagamento;
	}

	public void setPagamento(InfoPagamentoDetalhesDTO pagamento) {
		this.pagamento = pagamento;
	}
	
}
