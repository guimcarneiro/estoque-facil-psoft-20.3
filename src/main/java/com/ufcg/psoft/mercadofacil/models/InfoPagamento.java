package com.ufcg.psoft.mercadofacil.models;

import javax.persistence.Embeddable;

import com.ufcg.psoft.mercadofacil.enums.MetodoPagamento;

@Embeddable
public class InfoPagamento {
	
	private MetodoPagamento metodoPagamento;
	
	public InfoPagamento() {}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
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
