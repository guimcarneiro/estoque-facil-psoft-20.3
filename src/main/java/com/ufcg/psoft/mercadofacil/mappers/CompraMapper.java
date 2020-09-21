package com.ufcg.psoft.mercadofacil.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ufcg.psoft.mercadofacil.DTO.CompraDTO;
import com.ufcg.psoft.mercadofacil.DTO.CompraDetalhesDTO;
import com.ufcg.psoft.mercadofacil.DTO.CompraHistoricoDTO;
import com.ufcg.psoft.mercadofacil.DTO.InfoPagamentoDetalhesDTO;
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
	
	public static CompraHistoricoDTO mapCompraToCompraHistoricoDTO(Compra compra) {

		List<ProdutoCarrinhoDTO> produtosCarrinhoDTO = new ArrayList<ProdutoCarrinhoDTO>();
		
		for (ProdutoCarrinho produtoCarrinho : compra.getProdutos()) {
			ProdutoCarrinhoDTO pcd = ProdutoCarrinhoMapper
										.mapProdutoCarrinhoToProdutoCarrinhoDTO(produtoCarrinho);
			produtosCarrinhoDTO.add(pcd);
		}
		
		CompraHistoricoDTO compraHistoricoDTO = new CompraHistoricoDTO(
				compra.getId(),
				produtosCarrinhoDTO,
				compra.getValorTotal(),
				compra.getDataCriacao());
		
		return compraHistoricoDTO;
	}
	
	public static CompraDetalhesDTO mapCompraToCompraDetalhesDTO(Compra compra) {
		CompraDetalhesDTO compraDetalhesDTO = new CompraDetalhesDTO();
		
		InfoPagamentoDetalhesDTO infoPagamentoDetalhesDTO = InfoPagamentoMapper
				.mapInfoPagamentoToInfoPagamentoDetalhes(compra.getInfoPagamento());
		
		List<ProdutoCarrinhoDTO> produtosCarrinhoDTO = new ArrayList<ProdutoCarrinhoDTO>();
		for (ProdutoCarrinho produtoCarrinho : compra.getProdutos()) {
			ProdutoCarrinhoDTO pcd = ProdutoCarrinhoMapper
										.mapProdutoCarrinhoToProdutoCarrinhoDTO(produtoCarrinho);
			produtosCarrinhoDTO.add(pcd);
		}
		
		compraDetalhesDTO.setId(compra.getId());
		compraDetalhesDTO.setPagamento(infoPagamentoDetalhesDTO);
		compraDetalhesDTO.setProdutos(produtosCarrinhoDTO);
		compraDetalhesDTO.setDataCriacao(compra.getDataCriacao());
		compraDetalhesDTO.setValorTotal(compra.getValorTotal());
		
		return compraDetalhesDTO;
	}
	
}
