package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Categoria;

public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {

	Optional<Categoria> findByNomeCategoria(String nomeCategoria);

}
