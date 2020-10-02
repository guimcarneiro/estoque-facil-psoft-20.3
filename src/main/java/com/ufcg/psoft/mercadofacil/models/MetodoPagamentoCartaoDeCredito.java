package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;
import com.ufcg.psoft.mercadofacil.interfaces.InterfaceMetodoPagamento;

public class MetodoPagamentoCartaoDeCredito implements InterfaceMetodoPagamento {

	private final MetodoPagamento METODO_PAGAMENTO_CARTAO_DE_CREDITO = MetodoPagamento.CARTAO_DE_CREDITO;
	
	private final BigDecimal TAXA_PAGAMENTO_CARTAO_DE_CREDITO = new BigDecimal(1.05);
	
	@Override
	public MetodoPagamento getMetodoPagamento() {
		return this.METODO_PAGAMENTO_CARTAO_DE_CREDITO;
	}

	@Override
	public BigDecimal getAcrescimo() {
		return this.TAXA_PAGAMENTO_CARTAO_DE_CREDITO;
	}
	
}
