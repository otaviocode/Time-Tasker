package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.LogDeTempo;
import java.time.format.DateTimeFormatter;

@Repository
public interface RepositorioLogDeTempo extends JpaRepository<LogDeTempo, Long> {

	Optional<LogDeTempo> findByDuracao(String duracao);

	Optional<LogDeTempo> findByHoraFim(DateTimeFormatter horaFim);

	Optional<LogDeTempo> findByHoraInicio(DateTimeFormatter horaInicio);

	Optional<LogDeTempo> findById(Long id); // qualquer coisa exclui essa linha

}
