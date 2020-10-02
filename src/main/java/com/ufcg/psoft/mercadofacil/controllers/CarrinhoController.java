package com.ufcg.psoft.mercadofacil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.DTO.FinalizacaoCompraDTO;
import com.ufcg.psoft.mercadofacil.services.CarrinhoDeComprasService;
import com.ufcg.psoft.mercadofacil.services.CompraService;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin
public class CarrinhoController {
	
	@Autowired
	private CarrinhoDeComprasService carrinhoDeComprasService;
	
	@Autowired
	private CompraService compraService;
	
	@PutMapping(value = "/{idProduto}")
	public ResponseEntity<?> adicionarAoCarrinho(@PathVariable Long idProduto){
		return this.carrinhoDeComprasService.adicionarProduto(idProduto);
	}
	
	@GetMapping
	public ResponseEntity<?> listarProdutosCarrinho() {
		return this.carrinhoDeComprasService.listarProdutosCarrinhoDTO();
	}
	
	@PutMapping(value = "/limpar")
	public ResponseEntity<?> esvaziarCarrinho() {
		return this.carrinhoDeComprasService.esvaziarCarrinho();
	}
	
	@PostMapping(value = "/compra")
	public ResponseEntity<?> finalizarCompra(
			@RequestBody FinalizacaoCompraDTO finalizacaoCompraDTO) {
		ResponseEntity<?> resposta = this.compraService.finalizarCompra(
				this.carrinhoDeComprasService.getProdutosCarrinho(),
				finalizacaoCompraDTO);
		
		this.carrinhoDeComprasService.esvaziarCarrinho();
		
		return resposta;
	}
	
}
