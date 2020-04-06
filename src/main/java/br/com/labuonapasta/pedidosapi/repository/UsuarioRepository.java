package br.com.labuonapasta.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.labuonapasta.pedidosapi.model.Usuario;

/**
 * UsuarioRepository
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}