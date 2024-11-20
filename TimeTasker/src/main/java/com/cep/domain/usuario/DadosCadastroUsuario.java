package com.cep.domain.usuario;

//import jakarta.validation.constraints.NotBlank;

public class DadosCadastroUsuario {

	// @NotBlank(message = "Login não pode ser vazio")
	private String login;

	// @NotBlank(message = "Senha não pode ser vazia")
	private String senha;

	// Construtor padrão (necessário para o mapeamento automático)
	public DadosCadastroUsuario() {
	}

	// Construtor com argumentos (opcional, mas útil)
	public DadosCadastroUsuario(String login, String senha) {
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
