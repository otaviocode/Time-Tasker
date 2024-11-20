package com.example.demo.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Usuario;
import com.example.demo.repositories.RepositorioUsuario;

@Service
public class ServicoAutentificacao {

	private final RepositorioUsuario repositorioUsuario;
	private final PasswordEncoder passwordEncoder;

	// Injeção via construtor
	public ServicoAutentificacao(RepositorioUsuario repositorioUsuario, PasswordEncoder passwordEncoder) {
		this.repositorioUsuario = repositorioUsuario;
		this.passwordEncoder = passwordEncoder;
	}

	public void registerUsuario(String email, String senha, String nomeUsuario) {
		// Verificar se o email já está cadastrado
		if (repositorioUsuario.findByEmail(email).isPresent()) {
			throw new IllegalArgumentException("Email já cadastrado");
		}

		// Criptografar a senha antes de salvar
		String senhaCriptografada = passwordEncoder.encode(senha);

		// Criar e salvar o novo usuário (ID será gerado automaticamente)
		Usuario usuario = new Usuario(nomeUsuario, email, senhaCriptografada);
		repositorioUsuario.save(usuario);
	}
}
