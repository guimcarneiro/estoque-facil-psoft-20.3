package com.ufcg.psoft.mercadofacil.utils;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;
import com.ufcg.psoft.mercadofacil.interfaces.InterfaceMetodoPagamento;
import com.ufcg.psoft.mercadofacil.models.MetodoPagamentoBoleto;
import com.ufcg.psoft.mercadofacil.models.MetodoPagamentoCartaoDeCredito;
import com.ufcg.psoft.mercadofacil.models.MetodoPagamentoPaypal;

public class MetodoPagamentoUtils {

	public static InterfaceMetodoPagamento getMetodoPagamentoByEnum(MetodoPagamento metodoPagamentoEnum) {
		switch(metodoPagamentoEnum) {
			case BOLETO:
				return new MetodoPagamentoBoleto();
			case PAYPAL:
				return new MetodoPagamentoPaypal();
			case CARTAO_DE_CREDITO:
				return new MetodoPagamentoCartaoDeCredito();
			default:
				return null;
		}
	}
	
}
