package com.example.demo.infra.security;

public class DadosTokenJWT {

	private String token;

	// Construtor para inicializar o token
	public DadosTokenJWT(String tokenJWT) {
		this.token = tokenJWT;
	}

	// Getter para serialização correta
	public String getToken() {
		return token;
	}

	// Setter para permitir modificações
	public void setToken(String token) {
		this.token = token;
	}
}
