package com.ufcg.psoft.mercadofacil.models;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ufcg.psoft.mercadofacil.models.Produto;

import exceptions.ObjetoInvalidoException;

@Entity
public class Produto {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;

	private BigDecimal preco;

	private String codigoBarra;

	private String fabricante;

	private String categoria;
	
	private String descricao;

	public int situacao; // usa variaveis estaticas abaixo
	/* situacoes do produto */
	public static final int DISPONIVEL = 1;
	public static final int INDISPONIVEL = 2;

	public Produto() {
		this.preco = new BigDecimal(0);
	}

	public Produto(String nome, String codigoBarra, String fabricante,
			String nomeCategoria, String descricao) {
		super();
		this.nome = nome;
		this.preco = new BigDecimal(0);
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.categoria = nomeCategoria;
		this.situacao = Produto.INDISPONIVEL;
		this.descricao = descricao;
	}

	public Long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return this.preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void mudaFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCodigoBarra() {
		return this.codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void mudaCategoria(String categoria) {
		this.categoria = categoria;
	}
		
	public void mudaSituacao(int situacao) throws ObjetoInvalidoException {
		switch (situacao) {
		case 1:
			this.situacao = Produto.DISPONIVEL;
			break;
		case 2:
			this.situacao = Produto.INDISPONIVEL;
			break;

		default:
			throw new ObjetoInvalidoException("Situacao Invalida");
		}
	}

	public int getSituacao() throws ObjetoInvalidoException {
		return this.situacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigoBarra == null) ? 0 : codigoBarra.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + situacao;
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
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigoBarra == null) {
			if (other.codigoBarra != null)
				return false;
		} else if (!codigoBarra.equals(other.codigoBarra))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (situacao != other.situacao)
			return false;
		return true;
	}

	public String toString() {
		return this.id + " " + this.nome;
	}
	
}