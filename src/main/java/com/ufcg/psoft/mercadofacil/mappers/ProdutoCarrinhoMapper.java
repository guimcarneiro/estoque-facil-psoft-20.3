package com.ufcg.psoft.mercadofacil.mappers;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoCarrinhoDTO;
import com.ufcg.psoft.mercadofacil.models.ProdutoCarrinho;

public class ProdutoCarrinhoMapper {

	public static ProdutoCarrinhoDTO mapProdutoCarrinhoToProdutoCarrinhoDTO(ProdutoCarrinho produtoCarrinho) {
		
		ProdutoCarrinhoDTO produtoCarrinhoDTO = new ProdutoCarrinhoDTO(
				produtoCarrinho.getProduto(),
				produtoCarrinho.getQuantidadeProdutos()
				);
		
		return produtoCarrinhoDTO;
	}
	
}
