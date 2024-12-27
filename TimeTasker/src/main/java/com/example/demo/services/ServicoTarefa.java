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

	public Object buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tarefa salvar(Tarefa tarefa) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object atualizar(Long id, Tarefa tarefa) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tarefa> filtrar(String titulo, String prioridade, java.util.Date prazo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean excluir(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
