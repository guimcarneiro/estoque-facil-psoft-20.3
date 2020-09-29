package com.ufcg.psoft.mercadofacil.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.ufcg.psoft.mercadofacil.enums.PerfilUsuarioEnum;
import com.ufcg.psoft.mercadofacil.models.ProdutoCarrinho;

public interface InterfacePerfilUsuario {

	public PerfilUsuarioEnum getPerfil();
	
	public BigDecimal getDesconto(List<ProdutoCarrinho> produtos);
	
}
