package com.example.demo.controllers;

import com.example.demo.Tarefa;
import com.example.demo.services.ServicoTarefa;
import com.example.demo.repositories.RepositorioTarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
public class ControllerTarefa {

	@Autowired
	private RepositorioTarefa repositorioTarefa;

	@Autowired
	private ServicoTarefa servicoTarefa;

	// Endpoint para listar todas as tarefas
	@GetMapping
	public List<Tarefa> listarTarefas() {
		return servicoTarefa.buscarTodasTarefas();
	}

	// Endpoint para obter uma tarefa por ID
	@GetMapping("/{id}")
	public ResponseEntity<Tarefa> obterTarefa(@PathVariable Long id) {
		Optional<Tarefa> tarefa = repositorioTarefa.findById(id);
		return tarefa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// Endpoint para criar uma nova tarefa
	@PostMapping
	public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
		if (tarefa.getTitulo() == null || tarefa.getTitulo().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Tarefa novaTarefa = repositorioTarefa.save(tarefa);
		return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
	}

	// Endpoint para atualizar uma tarefa existente
	@PutMapping("/{id}")
	public ResponseEntity<Tarefa> editarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
		Optional<Tarefa> tarefaExistente = repositorioTarefa.findById(id);
		if (tarefaExistente.isPresent()) {
			tarefaAtualizada.setId(id);
			Tarefa tarefa = repositorioTarefa.save(tarefaAtualizada);
			return ResponseEntity.ok(tarefa);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	// Endpoint para excluir uma tarefa
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
		if (!repositorioTarefa.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		repositorioTarefa.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// Endpoint para buscar tarefas por título
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Tarefa>> buscarPorTitulo(@PathVariable String titulo) {
		@SuppressWarnings("unchecked")
		List<Tarefa> tarefas = (List<Tarefa>) repositorioTarefa.findByTitulo(titulo).orElse(null);
		if (tarefas == null || tarefas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(tarefas);
	}

	// Endpoint para buscar tarefas por prioridade
	@GetMapping("/prioridade/{prioridade}")
	public ResponseEntity<List<Tarefa>> buscarPorPrioridade(@PathVariable String prioridade) {
		@SuppressWarnings("unchecked")
		List<Tarefa> tarefas = (List<Tarefa>) repositorioTarefa.findByPrioridade(prioridade).orElse(null);
		if (tarefas == null || tarefas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(tarefas);
	}

	// Endpoint para buscar tarefas por prazo
	@GetMapping("/prazo/{prazo}")
	public ResponseEntity<List<Tarefa>> buscarPorPrazo(@PathVariable Date prazo) {
		@SuppressWarnings("unchecked")
		List<Tarefa> tarefas = (List<Tarefa>) repositorioTarefa.findByPrazo(prazo).orElse(null);
		if (tarefas == null || tarefas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(tarefas);
	}
}
