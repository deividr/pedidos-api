package br.com.labuonapasta.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.labuonapasta.pedidosapi.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}