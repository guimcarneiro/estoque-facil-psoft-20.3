package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;

public class InfoPagamentoDetalhesDTO {
	
	private MetodoPagamento metodoPagamento;
	
	public InfoPagamentoDetalhesDTO() {}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}
	
}
