package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repositories.RepositorioUsuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioControler {

	@Autowired
	RepositorioUsuario repositorioUsuario;

}
