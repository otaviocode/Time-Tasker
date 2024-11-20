package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cep.domain.usuario.DadosAutenticacao;

import com.cep.domain.usuario.DadosCadastroUsuario;

import com.example.demo.Usuario;

import com.example.demo.repositories.RepositorioUsuario;

import com.example.demo.infra.security.DadosTokenJWT;

import com.example.demo.infra.security.TokenService;

import jakarta.transaction.Transactional;

@RestController

@RequestMapping("/usuario")

public class UsuarioController {

	@Autowired

	private AuthenticationManager manager;

	@Autowired

	private TokenService tokenService;

	@Autowired

	private RepositorioUsuario repositorioUsuario;

	@Autowired

	private PasswordEncoder passwordEncoder;

	// Construtor com todas as dependências injetadas pelo Spring

	public UsuarioController(AuthenticationManager manager, TokenService tokenService,

			RepositorioUsuario repositorioUsuario, PasswordEncoder passwordEncoder) {

		this.manager = manager;

		this.tokenService = tokenService;

		this.repositorioUsuario = repositorioUsuario;

		this.passwordEncoder = passwordEncoder;

	}

	@PostMapping("/login")

	public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Validated DadosAutenticacao dados) {

		var authenticationToken = new UsernamePasswordAuthenticationToken(dados.getLogin(), dados.getSenha());

		var authentication = manager.authenticate(authenticationToken);

		var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

	}

	@PostMapping("/cadastrar")

	@Transactional

	public ResponseEntity<String> cadastrar(@RequestBody @Validated DadosCadastroUsuario dados) {

		if (repositorioUsuario.findByLogin(dados.getLogin()).isEmpty()) {

			String senha = passwordEncoder.encode(dados.getSenha());

			Usuario usuario = new Usuario(null, dados.getLogin(), senha);

			repositorioUsuario.save(usuario);

			return ResponseEntity.ok("Cadastrado com Sucesso!");

		} else {

			return ResponseEntity.badRequest().body("Usuário já Existe!");

		}

	}

}
