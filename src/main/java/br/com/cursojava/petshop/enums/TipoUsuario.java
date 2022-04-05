package br.com.cursojava.petshop.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuario {
	USUARIO("USUARIO", "Usuário"),
	ADMIN("ADMIN", "ADMIN"),
	SUPERADMIN("SUPERADMIN", "Super administrador");

	private String chave;
	
	private String descricao;
	
	TipoUsuario(String chave, String descricao) {
		this.chave = chave;
		this.descricao = descricao;
	}
	
	public String getChave() {
		return chave;
	}
	
	public void setChave(String chave) {
		this.chave = chave;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@JsonValue
	public String getValor() {
		switch (this) {
			case ADMIN:
			case USUARIO:
				return getChave();
		}
		return "Inválido";
	}
}
