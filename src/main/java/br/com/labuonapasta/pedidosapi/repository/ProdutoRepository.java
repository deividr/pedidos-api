package br.com.labuonapasta.pedidosapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.labuonapasta.pedidosapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}