package com.ufcg.psoft.mercadofacil.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;
import com.ufcg.psoft.mercadofacil.interfaces.InterfaceMetodoPagamento;
import com.ufcg.psoft.mercadofacil.utils.MetodoPagamentoUtils;

@Embeddable
public class InfoPagamento {
	
	@Column(name = "metodo_pagamento")
	private MetodoPagamento metodoPagamentoEnum;
	
	@Transient
	private InterfaceMetodoPagamento metodoPagamento;
	
	public InfoPagamento() {}
	
	public InfoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamentoEnum = metodoPagamento;
	}
	
	// Métodos para persistir enums e converter enum em Classe Strategy novamente
	
	@PostLoad
	@PostPersist
	@PostUpdate
	void fillMetodoPagamentoTransient() {
		this.metodoPagamento = MetodoPagamentoUtils.getMetodoPagamentoByEnum(this.metodoPagamentoEnum);
	}
	
	public InterfaceMetodoPagamento getMetodoPagamento() {
		return this.metodoPagamento;
	}

	public void setMetodoPagamento(InterfaceMetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public MetodoPagamento getMetodoPagamentoEnum() {
		return metodoPagamentoEnum;
	}

	public void setMetodoPagamentoEnum(MetodoPagamento metodoPagamentoEnum) {
		this.metodoPagamentoEnum = metodoPagamentoEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metodoPagamento == null) ? 0 : metodoPagamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoPagamento other = (InfoPagamento) obj;
		if (metodoPagamento != other.metodoPagamento)
			return false;
		return true;
	}
	
}
