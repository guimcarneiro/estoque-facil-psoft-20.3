package com.ufcg.psoft.mercadofacil.utils;

import java.util.List;

import com.ufcg.psoft.mercadofacil.models.ProdutoCarrinho;

public class CarrinhoUtils {

	public static int getQuantidadeItens(List<ProdutoCarrinho> produtos) {
		int total = 0;
		
		for (ProdutoCarrinho produto : produtos) {
			total += produto.getQuantidadeProdutos();
		}
		
		return total;
	}
	
}
