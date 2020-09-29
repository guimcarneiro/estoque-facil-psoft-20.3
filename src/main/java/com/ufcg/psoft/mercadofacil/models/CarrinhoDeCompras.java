package com.ufcg.psoft.mercadofacil.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CarrinhoDeCompras {

	private List<ProdutoCarrinho> produtos;
	
	public CarrinhoDeCompras () {
		this.produtos = new ArrayList<ProdutoCarrinho>();
	}
	
	public void esvaziar() {
		this.produtos = new ArrayList<ProdutoCarrinho>();
	}
	
	public ProdutoCarrinho adicionarProduto(Produto produto) {

		ProdutoCarrinho produtoCarrinho = searchByProduto(produto);
		ProdutoCarrinho produtoCarrinhoSalvo = null;
		
		if(produtoCarrinho == null) {
			produtoCarrinhoSalvo = new ProdutoCarrinho(produto, 1);
			this.produtos.add(produtoCarrinhoSalvo);
		} else {
			int prodCarrinhoIndex = this.produtos.indexOf(produtoCarrinho);
			
			produtoCarrinhoSalvo = this.produtos.get(prodCarrinhoIndex);
			produtoCarrinhoSalvo.setQuantidadeProdutos(produtoCarrinhoSalvo.getQuantidadeProdutos() + 1);
			
		}
		
		return produtoCarrinhoSalvo;
	}
	
	public List<ProdutoCarrinho> listarProdutos() {
		return this.produtos;
	}
	
	public boolean isVazio() { 
		return this.produtos.isEmpty();
	}
	
	private ProdutoCarrinho searchByProduto(Produto produto) {
		for (ProdutoCarrinho produtoCarrinho : this.produtos) {
			if(produto.equals(produtoCarrinho.getProduto())) {
				return produtoCarrinho;
			}
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
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
		CarrinhoDeCompras other = (CarrinhoDeCompras) obj;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CarrinhoDeCompras [produtos=" + produtos + "]";
	}
	
}
