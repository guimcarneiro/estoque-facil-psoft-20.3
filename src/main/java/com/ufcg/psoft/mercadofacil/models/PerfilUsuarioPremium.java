package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;
import java.util.List;

import com.ufcg.psoft.mercadofacil.enums.PerfilUsuarioEnum;
import com.ufcg.psoft.mercadofacil.interfaces.InterfacePerfilUsuario;
import com.ufcg.psoft.mercadofacil.utils.CarrinhoUtils;

public class PerfilUsuarioPremium implements InterfacePerfilUsuario{

private final PerfilUsuarioEnum perfil = PerfilUsuarioEnum.PREMIUM;
	
	private final BigDecimal DESCONTO_USUARIO_PREMIUM = new BigDecimal(1.1);
	private final int NUMERO_DE_ITENS_MINIMO_PARA_APLICAR_DESCONTO = 5;
	
	@Override
	public PerfilUsuarioEnum getPerfil() {
		return this.perfil;
	}

	@Override
	public BigDecimal getDesconto(List<ProdutoCarrinho> produtos) {
		int qtdItens = CarrinhoUtils.getQuantidadeItens(produtos);
		
		if(qtdItens > NUMERO_DE_ITENS_MINIMO_PARA_APLICAR_DESCONTO) {
			return DESCONTO_USUARIO_PREMIUM;
		}
		
		return new BigDecimal(1);
	}
	
	
	
}
