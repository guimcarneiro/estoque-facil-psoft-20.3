package com.ufcg.psoft.mercadofacil.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProdutoCarrinho {
	
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Produto produto;
	
	private int quantidadeProdutos;
	
	public ProdutoCarrinho() {}
	
	public ProdutoCarrinho(Produto produto, int quantidadeProdutos) {
		this.produto = produto;
		this.quantidadeProdutos = quantidadeProdutos;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + quantidadeProdutos;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoCarrinho other = (ProdutoCarrinho) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidadeProdutos != other.quantidadeProdutos)
			return false;
		return true;
	}
	
}
