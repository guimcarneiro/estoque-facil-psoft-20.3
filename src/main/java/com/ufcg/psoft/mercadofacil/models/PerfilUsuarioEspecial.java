package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;
import java.util.List;

import com.ufcg.psoft.mercadofacil.enums.PerfilUsuarioEnum;
import com.ufcg.psoft.mercadofacil.interfaces.InterfacePerfilUsuario;
import com.ufcg.psoft.mercadofacil.utils.CarrinhoUtils;

public class PerfilUsuarioEspecial implements InterfacePerfilUsuario {

	private final PerfilUsuarioEnum perfil = PerfilUsuarioEnum.ESPECIAL;
	private final BigDecimal DESCONTO_PERFIL_ESPECIAL = new BigDecimal(0.9);
	private final int NUMERO_DE_ITENS_MINIMO_PARA_APLICAR_DESCONTO = 10;
	
	@Override
	public PerfilUsuarioEnum getPerfil() {
		return this.perfil;
	}

	@Override
	public BigDecimal getDesconto(List<ProdutoCarrinho> produtos) {
		
		int qtdItens = CarrinhoUtils.getQuantidadeItens(produtos);
		
		if(qtdItens > NUMERO_DE_ITENS_MINIMO_PARA_APLICAR_DESCONTO) {
			return DESCONTO_PERFIL_ESPECIAL;
		}
		
		return new BigDecimal(1);
	}
	
}
