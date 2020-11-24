package com.ufcg.psoft.mercadofacil.DTO;

import com.ufcg.psoft.mercadofacil.enums.PerfilUsuarioEnum;

public class UsuarioDTO {
	
	private Long id;
	
	private String cpf;
	
	private String nome;
	
	private String email;
	
	private PerfilUsuarioEnum perfil;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PerfilUsuarioEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuarioEnum perfil) {
		this.perfil = perfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
