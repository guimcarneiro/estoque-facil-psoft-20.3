package com.ufcg.psoft.mercadofacil.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.mercadofacil.DTO.ProdutoCarrinhoDTO;
import com.ufcg.psoft.mercadofacil.mappers.ProdutoCarrinhoMapper;
import com.ufcg.psoft.mercadofacil.models.CarrinhoDeCompras;
import com.ufcg.psoft.mercadofacil.models.Lote;
import com.ufcg.psoft.mercadofacil.models.Produto;
import com.ufcg.psoft.mercadofacil.models.ProdutoCarrinho;
import com.ufcg.psoft.mercadofacil.repositories.LoteRepository;
import com.ufcg.psoft.mercadofacil.repositories.ProdutoRepository;
import com.ufcg.psoft.mercadofacil.utils.CustomErrorType;

import exceptions.ObjetoInvalidoException;

@Service
public class CarrinhoDeComprasService {

	@Autowired
	private CarrinhoDeCompras carrinhoDeCompras;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private LoteRepository loteRepository;
	
	public ResponseEntity<?> adicionarProduto(Long produtoId) {
		
		Optional<Produto> produtoOpt = this.produtoRepository.findById(produtoId);
		
		//Confere se produto existe
		if(!produtoOpt.isPresent()) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto Inexistente"), HttpStatus.NOT_FOUND);
		}
		
		Produto produto = produtoOpt.get();
		
		//Confere se o produto está disponível
		try {
			if(produto.getSituacao() == 2) {
				return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto Indisponível"), HttpStatus.BAD_REQUEST);
			}
		} catch (ObjetoInvalidoException e) {
			return new ResponseEntity<ObjetoInvalidoException>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//Confere lote do produto
		Lote loteProduto = this.loteRepository.findByProdutoId(produto.getId());
		if(loteProduto == null) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Produto sem lote"), HttpStatus.NOT_FOUND);
		}
		if(loteProduto.getNumeroDeItens() <= 0) {
			return new ResponseEntity<CustomErrorType>(new CustomErrorType("Estoque desse produto esgotado"), HttpStatus.BAD_REQUEST);
		}
		
		//Adiciona produto ao carrinho e retorna DTO
		ProdutoCarrinho produtoCarrinho = this.carrinhoDeCompras.adicionarProduto(produto);
		
		ProdutoCarrinhoDTO produtoCarrinhoDTO = ProdutoCarrinhoMapper
													.mapProdutoCarrinhoToProdutoCarrinhoDTO(produtoCarrinho);
		
		return new ResponseEntity<ProdutoCarrinhoDTO>(produtoCarrinhoDTO, HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> esvaziarCarrinho(){
		this.carrinhoDeCompras.esvaziar();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public List<ProdutoCarrinho> getProdutosCarrinho(){
		return this.carrinhoDeCompras.listarProdutos();
	}
	
	public ResponseEntity<?> listarProdutosCarrinhoDTO(){
		List<ProdutoCarrinhoDTO> produtosCarrinhoDTO = new ArrayList<ProdutoCarrinhoDTO>();
		
		for (ProdutoCarrinho produtoCarrinho : this.carrinhoDeCompras.listarProdutos()) {
			ProdutoCarrinhoDTO prodCarrinhoDTO = ProdutoCarrinhoMapper
													.mapProdutoCarrinhoToProdutoCarrinhoDTO(produtoCarrinho);
			
			produtosCarrinhoDTO.add(prodCarrinhoDTO);
		}
		
		return new ResponseEntity<List<ProdutoCarrinhoDTO>>(produtosCarrinhoDTO, HttpStatus.OK);
	}

}
