package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Login;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	Login login = new Login();

	@GetMapping(value = "/{login}")
	public String login(@PathVariable("nome") String nome) {
		return "Olá" + nome;
	}

}