package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Compra {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<ProdutoCarrinho> produtosCarrinho;
	
	private BigDecimal valorTotal;
	
	private LocalDateTime dataCriacao;
	
	public Compra() {}

	public Compra(List<ProdutoCarrinho> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
		this.valorTotal = this.somaValorProdutos(produtosCarrinho);
		this.dataCriacao = LocalDateTime.now();
	}

	public List<ProdutoCarrinho> getProdutos() {
		return produtosCarrinho;
	}

	public void setProdutos(List<ProdutoCarrinho> produtos) {
		this.produtosCarrinho = produtos;
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
	
	private BigDecimal somaValorProdutos(List<ProdutoCarrinho> produtosCarrinho) {
		BigDecimal valorTotal = new BigDecimal(0);
		
		for (ProdutoCarrinho produtoCarrinho : produtosCarrinho) {
			valorTotal = valorTotal.add(
					produtoCarrinho.getProduto().getPreco())
					.multiply(new BigDecimal(produtoCarrinho.getQuantidadeProdutos()));
		}
		
		return valorTotal;
	}
	
}