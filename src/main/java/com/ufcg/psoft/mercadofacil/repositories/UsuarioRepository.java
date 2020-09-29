package com.ufcg.psoft.mercadofacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufcg.psoft.mercadofacil.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
}
