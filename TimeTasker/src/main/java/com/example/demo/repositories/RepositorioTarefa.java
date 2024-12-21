package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Tarefa;
import java.sql.Date;

public interface RepositorioTarefa extends JpaRepository<Tarefa, Long> {

	List<Tarefa> findByTitulo(String titulo);

	List<Tarefa> findByDescricao(String descricao);

	List<Tarefa> findByPrazo(Date prazo);

	List<Tarefa> findByPrioridade(String prioridade);

}
