package com.ufcg.psoft.mercadofacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.mercadofacil.models.Lote;

public interface LoteRepository extends JpaRepository<Lote, Long>{
	
	public Lote findByProdutoId(Long id);
	
}