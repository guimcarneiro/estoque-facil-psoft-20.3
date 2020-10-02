package com.ufcg.psoft.mercadofacil.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.ufcg.psoft.mercadofacil.enums.PerfilUsuarioEnum;
import com.ufcg.psoft.mercadofacil.interfaces.InterfacePerfilUsuario;
import com.ufcg.psoft.mercadofacil.utils.PerfilUsuarioUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	
	private String cpf;
	
	private String nome;
	
	private String email;
	
	@Column(name = "perfil")
	private PerfilUsuarioEnum perfilEnum;
	
	@Transient
	private InterfacePerfilUsuario perfil;
	
	public Usuario() {}
	
	// Métodos para persistir enums e converter enum em Classe Strategy novamente
	
	@PostLoad
	@PostPersist
	@PostUpdate
	void fillPerfilTransient() {
		this.perfil = PerfilUsuarioUtils.getPerfilUsuarioByEnum(this.perfilEnum);
	}

	@PrePersist
	void fillPerfilEnumPersistant() {
		this.perfilEnum = PerfilUsuarioEnum.NORMAL;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public InterfacePerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(InterfacePerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}

	public PerfilUsuarioEnum getPerfilEnum() {
		return perfilEnum;
	}

	public void setPerfilEnum(PerfilUsuarioEnum perfilEnum) {
		this.perfilEnum = perfilEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		return true;
	}
	
}
