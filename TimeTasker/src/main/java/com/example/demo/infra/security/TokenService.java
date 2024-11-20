package com.example.demo.infra.security;

import org.springframework.stereotype.Service;

import com.example.demo.Usuario;

@Service
public class TokenService {
	// Métodos para geração, validação e manipulação de tokens
	public String gerarToken(Usuario usuario) {
		// lógica para gerar o token
		return "token-gerado";
	}

	public boolean validateToken(String token) {
		// lógica para validar o token
		return true;
	}
}
