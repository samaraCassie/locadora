package com.ifsc.tds.Samara.Leticia.entity;

import java.sql.Date;

public class Locacao {
	private Long id;
	private String observacao;
	private String nomeFilme;
	private Date DataLocacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getNomeFilme() {
		return nomeFilme;
	}
	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}
	public Date getDataLocacao() {
		return DataLocacao;
	}
	public void setDataLocacao(Date dataLocacao) {
		DataLocacao = dataLocacao;
	}
}
