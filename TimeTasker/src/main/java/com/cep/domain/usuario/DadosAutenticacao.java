package com.cep.domain.usuario;

public class DadosAutenticacao {

	// @NotBlank
	private String login;

	// @NotBlank
	private String senha;

	// Construtor padrão
	public void DadosAutentificacao() {
	}

	// Construtor com argumentos
	public void DadosAutentificacao(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	// Getters e Setters
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
