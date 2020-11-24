package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;
import com.ufcg.psoft.mercadofacil.interfaces.InterfaceMetodoPagamento;

public class MetodoPagamentoPaypal implements InterfaceMetodoPagamento{

	private final MetodoPagamento METODO_PAGAMENTO_PAYPAL = MetodoPagamento.PAYPAL;
	
	private final BigDecimal TAXA_PAGAMENTO_PAYPAL = new BigDecimal(1.02);
	
	@Override
	public MetodoPagamento getMetodoPagamento() {
		return this.METODO_PAGAMENTO_PAYPAL;
	}

	@Override
	public BigDecimal getAcrescimo() {
		return this.TAXA_PAGAMENTO_PAYPAL;
	}

	
	
}
