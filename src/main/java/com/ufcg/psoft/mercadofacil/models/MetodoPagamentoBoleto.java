package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;
import com.ufcg.psoft.mercadofacil.interfaces.InterfaceMetodoPagamento;

public class MetodoPagamentoBoleto implements InterfaceMetodoPagamento {

	private final MetodoPagamento METODO_PAGAMENTO_BOLETO = MetodoPagamento.BOLETO;
	
	private final BigDecimal TAXA_PAGAMENTO_BOLETO = new BigDecimal(1.0);
	
	@Override
	public MetodoPagamento getMetodoPagamento() {
		return this.METODO_PAGAMENTO_BOLETO;
	}

	@Override
	public BigDecimal getAcrescimo() {
		return this.TAXA_PAGAMENTO_BOLETO;
	}

	
	
}
