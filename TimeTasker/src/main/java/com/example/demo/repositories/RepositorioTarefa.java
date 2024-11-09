package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Tarefa;
import java.sql.Date;

public interface RepositorioTarefa extends JpaRepository<Tarefa, Long> {

	Optional<Tarefa> findByTitulo(String titulo);

	Optional<Tarefa> findByDescricao(String descricao);

	Optional<Tarefa> findByPrazo(Date prazo);

	Optional<Tarefa> findByPrioridade(String prioridade);

	Optional<Tarefa> findById(Long id); // qualquer coisa exclui essa linha

}
