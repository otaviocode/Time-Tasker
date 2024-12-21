package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cep.domain.usuario.DadosAutenticacao;
import com.example.demo.Usuario;
import com.example.demo.infra.security.DadosTokenJWT;
import com.example.demo.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<DadosTokenJWT> login(@RequestBody @Validated DadosAutenticacao dados) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					dados.getLogin(), dados.getSenha());

			Authentication authentication = authenticationManager.authenticate(authenticationToken);

			// Verifica se o principal é do tipo Usuario e gera o token
			if (authentication.getPrincipal() instanceof Usuario) {
				String token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
				return ResponseEntity.ok(new DadosTokenJWT(token));
			} else {
				throw new UsernameNotFoundException("Usuário não encontrado.");
			}
		} catch (Exception e) {
			// Se ocorrer qualquer exceção, retorna um erro genérico
			return ResponseEntity.status(500).body(new DadosTokenJWT("Erro ao autenticar: " + e.getMessage()));
		}
	}

	// Tratamento de exceção para falha de autenticação
	@ExceptionHandler({ org.springframework.security.authentication.BadCredentialsException.class })
	public ResponseEntity<String> handleAuthenticationException() {
		return ResponseEntity.status(401).body("Credenciais inválidas.");
	}

	// Tratamento de exceção para problemas não esperados
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<String> handleGeneralException(Exception e) {
		return ResponseEntity.status(500).body("Erro interno no servidor: " + e.getMessage());
	}
}
