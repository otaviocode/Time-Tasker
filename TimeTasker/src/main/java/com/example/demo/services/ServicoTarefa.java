package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Tarefa;
import com.example.demo.repositories.RepositorioTarefa;

@Service
public class ServicoTarefa {

	private RepositorioTarefa repositorioTarefa;

	public ServicoTarefa(RepositorioTarefa repositorioTarefa) {
		this.repositorioTarefa = repositorioTarefa;
	}

	public List<Tarefa> buscarTodasTarefas() {
		return repositorioTarefa.findAll();
	}
}
