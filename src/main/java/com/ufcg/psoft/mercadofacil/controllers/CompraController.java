package com.ufcg.psoft.mercadofacil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.mercadofacil.services.CompraService;

@RestController
@RequestMapping("/compra")
@CrossOrigin
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	@GetMapping
	public ResponseEntity<?> listaHistoricoCompras(){
		return this.compraService.listaHistoricoCompras();
	}
	
	@GetMapping("/{compraId}")
	public ResponseEntity<?> buscaCompraPorId(@PathVariable Long compraId){
		return this.compraService.buscaCompraPorId(compraId);
	}
	
}
