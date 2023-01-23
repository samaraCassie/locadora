package com.ifsc.tds.samara.leticia.entity;

public class Filme {
	private Long id;
	private String nome;
	private String email;
	private String fone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getFone() {
		return fone;
	}
}
