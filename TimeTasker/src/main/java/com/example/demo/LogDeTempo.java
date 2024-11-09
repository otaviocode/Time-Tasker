package com.example.demo;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "LogDeTempo")
public class LogDeTempo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private DateTimeFormatter horaInicio;
	private DateTimeFormatter horaFim;
	private String duracao;

	// Relacionamento
	@OneToOne // qualquer coisa mudar
	private LogDeTempo logDeTempo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTimeFormatter getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(DateTimeFormatter horaInicio) {
		this.horaInicio = horaInicio;
	}

	public DateTimeFormatter getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(DateTimeFormatter horaFim) {
		this.horaFim = horaFim;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public LogDeTempo getLogDeTempo() {
		return logDeTempo;
	}

	public void setLogDeTempo(LogDeTempo logDeTempo) {
		this.logDeTempo = logDeTempo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogDeTempo other = (LogDeTempo) obj;
		return Objects.equals(id, other.id);
	}

}
