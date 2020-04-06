package br.com.labuonapasta.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.labuonapasta.pedidosapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}