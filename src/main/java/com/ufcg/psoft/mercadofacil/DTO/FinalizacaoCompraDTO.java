package com.ufcg.psoft.mercadofacil.DTO;

public class FinalizacaoCompraDTO {

	private InfoPagamentoFinalizacaoCompraDTO infoPagamento;
	
	private Long usuarioId;
	
	public InfoPagamentoFinalizacaoCompraDTO getInfoPagamento() {
		return infoPagamento;
	}

	public void setInfoPagamento(InfoPagamentoFinalizacaoCompraDTO infoPagamento) {
		this.infoPagamento = infoPagamento;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
	
}
