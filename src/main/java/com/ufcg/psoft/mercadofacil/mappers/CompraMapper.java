package com.ufcg.psoft.mercadofacil.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ufcg.psoft.mercadofacil.DTO.CompraDTO;
import com.ufcg.psoft.mercadofacil.DTO.ProdutoCarrinhoDTO;
import com.ufcg.psoft.mercadofacil.models.Compra;
import com.ufcg.psoft.mercadofacil.models.ProdutoCarrinho;

public class CompraMapper {

	public static CompraDTO mapCompraToCompraDTO(Compra compra) {

		List<ProdutoCarrinhoDTO> produtosCarrinhoDTO = new ArrayList<ProdutoCarrinhoDTO>();
		
		for (ProdutoCarrinho produtoCarrinho : compra.getProdutos()) {
			ProdutoCarrinhoDTO pcd = ProdutoCarrinhoMapper
										.mapProdutoCarrinhoToProdutoCarrinhoDTO(produtoCarrinho);
			produtosCarrinhoDTO.add(pcd);
		}
		
		CompraDTO compraDTO = new CompraDTO(
				compra.getId(),
				produtosCarrinhoDTO,
				compra.getValorTotal(),
				compra.getDataCriacao());
		
		return compraDTO;
	}
	
}
