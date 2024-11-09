package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Usuario;

public interface RepositorioUsuario<ID> extends JpaRepository<Usuario, ID> {

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findBySenha(String senha);

}
