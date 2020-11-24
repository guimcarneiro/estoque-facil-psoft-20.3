package com.ufcg.psoft.mercadofacil.mappers;

import com.ufcg.psoft.mercadofacil.DTO.InfoPagamentoDetalhesDTO;
import com.ufcg.psoft.mercadofacil.DTO.InfoPagamentoFinalizacaoCompraDTO;
import com.ufcg.psoft.mercadofacil.models.InfoPagamento;
import com.ufcg.psoft.mercadofacil.utils.MetodoPagamentoUtils;

public class InfoPagamentoMapper {

	public static InfoPagamento mapInfoPagamentoFinalizacaoCompraDTOToInfoPagamento(InfoPagamentoFinalizacaoCompraDTO ipfcDTO) {
		InfoPagamento infoPagamento = new InfoPagamento();
		
		infoPagamento.setMetodoPagamento(MetodoPagamentoUtils.getMetodoPagamentoByEnum(ipfcDTO.getMetodoPagamento()));
		infoPagamento.setMetodoPagamentoEnum(ipfcDTO.getMetodoPagamento());
		
		return infoPagamento;
	}
	
	public static InfoPagamentoDetalhesDTO mapInfoPagamentoToInfoPagamentoDetalhes(InfoPagamento infoPagamento) {
		InfoPagamentoDetalhesDTO ipdDTO = new InfoPagamentoDetalhesDTO();
		
		ipdDTO.setMetodoPagamento(infoPagamento.getMetodoPagamentoEnum());
		
		return ipdDTO;
	}
	
}
