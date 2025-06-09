package com.projeto.inventario.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.inventario.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{

	Optional<Usuario> findByLogin(String login);
}
