package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;
import java.util.List;

import com.ufcg.psoft.mercadofacil.enums.PerfilUsuarioEnum;
import com.ufcg.psoft.mercadofacil.interfaces.InterfacePerfilUsuario;

public class PerfilUsuarioNormal implements InterfacePerfilUsuario{

	private final PerfilUsuarioEnum perfil = PerfilUsuarioEnum.NORMAL;
	
	private final BigDecimal DESCONTO_USUARIO_NORMAL = new BigDecimal(1);
	
	@Override
	public PerfilUsuarioEnum getPerfil() {
		return this.perfil;
	}

	@Override
	public BigDecimal getDesconto(List<ProdutoCarrinho> produtos) {
		return DESCONTO_USUARIO_NORMAL;
	}
	
	
	
}
