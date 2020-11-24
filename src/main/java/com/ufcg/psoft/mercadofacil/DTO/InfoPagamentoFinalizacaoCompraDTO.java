package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;

public class InfoPagamentoFinalizacaoCompraDTO {

	private MetodoPagamento metodoPagamento;
	
	public InfoPagamentoFinalizacaoCompraDTO() {}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

}
