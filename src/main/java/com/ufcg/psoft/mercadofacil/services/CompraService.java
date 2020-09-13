package com.ufcg.psoft.mercadofacil.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.CompraDTO;
import com.ufcg.psoft.mercadofacil.mappers.CompraMapper;
import com.ufcg.psoft.mercadofacil.models.Compra;
import com.ufcg.psoft.mercadofacil.models.Lote;
import com.ufcg.psoft.mercadofacil.models.Produto;
import com.ufcg.psoft.mercadofacil.models.ProdutoCarrinho;
import com.ufcg.psoft.mercadofacil.repositories.CompraRepository;
import com.ufcg.psoft.mercadofacil.repositories.LoteRepository;
import com.ufcg.psoft.mercadofacil.utils.CustomErrorType;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@Transactional
	public ResponseEntity<?> finalizarCompra(List<ProdutoCarrinho> produtosCarrinho){
		
		if(produtosCarrinho.isEmpty()) {
			return new ResponseEntity<CustomErrorType>(
					new CustomErrorType("Não é permitido finalizar uma compra com o carrinho vazio"),
					HttpStatus.NOT_FOUND
				);
		}
		
		if(!isLotesProdutosCarrinhoDeComprasValidos(produtosCarrinho)) {
			return new ResponseEntity<CustomErrorType>(
				new CustomErrorType("Não temos em estoque suficiente todos os produtos desejados"),
				HttpStatus.CONFLICT
				);
		}
		
		Compra compraASerFinalizada = new Compra(produtosCarrinho);
		Compra compraFinalizada = this.compraRepository.save(compraASerFinalizada);
		
		atualizaLotesProdutosCarrinhoDeCompras(produtosCarrinho);		
		
		CompraDTO compraFinalizadaDTO = CompraMapper.mapCompraToCompraDTO(compraFinalizada);
		
		return new ResponseEntity<CompraDTO>(compraFinalizadaDTO, HttpStatus.CREATED);
	}
	
	private boolean isLotesProdutosCarrinhoDeComprasValidos(List<ProdutoCarrinho> produtosCarrinho) {
	
		for (ProdutoCarrinho produtoCarrinho : produtosCarrinho) {
			
			Produto produto = produtoCarrinho.getProduto();
			int quantidadeProdutoCarrinho = produtoCarrinho.getQuantidadeProdutos();
			
			Lote loteDoProduto = this.loteRepository.findByProdutoId(produto.getId());
			long quantidadeProdutoLote = loteDoProduto.getNumeroDeItens();
			
			if(quantidadeProdutoLote <= 0 || quantidadeProdutoCarrinho > quantidadeProdutoLote) {
				return false;
			}
		}
	
		return true;
	}

	@Transactional
	private void atualizaLotesProdutosCarrinhoDeCompras(List<ProdutoCarrinho> produtosCarrinho) {
		
		for (ProdutoCarrinho produtoCarrinho : produtosCarrinho) {
			
			Produto produto = produtoCarrinho.getProduto();
			int quantidadeProdutoCarrinho = produtoCarrinho.getQuantidadeProdutos();
			
			
			Lote loteDoProduto = this.loteRepository.findByProdutoId(produto.getId());
			long qtdProdutoLote = loteDoProduto.getNumeroDeItens();
			
			//FIXME: lançar exception se o numero de produtos no carrinho for menor que o existente no lote
			
			int totalProdutosLotePosCompra = (int)(qtdProdutoLote - quantidadeProdutoCarrinho);
			
			loteDoProduto.setNumeroDeItens(totalProdutosLotePosCompra);
			
			this.loteRepository.save(loteDoProduto);
		}
	}
	
}
