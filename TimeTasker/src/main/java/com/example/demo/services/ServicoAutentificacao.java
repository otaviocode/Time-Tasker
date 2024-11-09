package com.example.demo.services;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Usuario;
import com.example.demo.repositories.RepositorioUsuario;

@Service
public class ServicoAutentificacao {

	@Autowired
	private RepositorioUsuario repositorioUsuario;

	public void registerUsuario(String email, String senha, String nomeUsuario) {
		if (repositorioUsuario.findByEmail(email).isPresent()) {
			throw new RuntimeErrorException(null, "Email já cadastrado");
		}
	}

	Usuario usuario = new Usuario();
	

}
