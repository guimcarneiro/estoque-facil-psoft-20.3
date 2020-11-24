package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Compra {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<ProdutoCarrinho> produtosCarrinho;
	
	private BigDecimal valorTotal;
	
	@Embedded
	private InfoPagamento infoPagamento;
	
	private LocalDateTime dataCriacao;
	
	public Compra() {}

	public Compra(
			List<ProdutoCarrinho> produtosCarrinho,
			InfoPagamento infoPagamento,
			Usuario usuario) {
		this.produtosCarrinho = produtosCarrinho;
		this.usuario = usuario;
		this.infoPagamento = infoPagamento;
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
	
	public InfoPagamento getInfoPagamento() {
		return this.infoPagamento;
	}

	public void setInfoPagamento(InfoPagamento infoPagamento) {
		this.infoPagamento = infoPagamento;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private BigDecimal somaValorProdutos(List<ProdutoCarrinho> produtosCarrinho) {
		BigDecimal valorTotal = new BigDecimal(0);

		for (ProdutoCarrinho produtoCarrinho : produtosCarrinho) {
			valorTotal = valorTotal.add(
					produtoCarrinho.getSubtotal());
		}
		
		BigDecimal porcentagemDescontoUsuario = usuario.getPerfil().getDesconto(produtosCarrinho);
		
		BigDecimal valorComDescontoDeUsuario = valorTotal.multiply(porcentagemDescontoUsuario);
		
		BigDecimal valorComDescontoeAcrescimo = valorComDescontoDeUsuario
				.multiply(
						this.infoPagamento.getMetodoPagamento().getAcrescimo());
		
		return valorComDescontoeAcrescimo;
	}
	
}
