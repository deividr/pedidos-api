package br.com.labuonapasta.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.labuonapasta.pedidosapi.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}