package com.ufcg.psoft.mercadofacil.interfaces;

import java.math.BigDecimal;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;

public interface InterfaceMetodoPagamento {

	public MetodoPagamento getMetodoPagamento();
	
	public BigDecimal getAcrescimo();
	
}
