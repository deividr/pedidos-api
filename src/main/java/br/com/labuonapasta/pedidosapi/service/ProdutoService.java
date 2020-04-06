package br.com.labuonapasta.pedidosapi.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.labuonapasta.pedidosapi.model.Produto;
import br.com.labuonapasta.pedidosapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   * Alterar um produto.
   * 
   * @param codigo  código chave do produto que será alterado.
   * @param produto
   * @return
   */
  public Produto alterar(Long codigo, @Valid Produto produto) {
    Produto produtoSalvo = buscarPeloCodigo(codigo);

    BeanUtils.copyProperties(produto, produtoSalvo, "prodId");

    return produtoRepository.save(produtoSalvo);
  }

  /**
   * Buscar um produto pelo seu código (chave primária da tabela).
   * 
   * @param codigo código do produto que é a chave primária da tabela.
   * @return Produto encontrado com aquele código
   */
  public Produto buscarPeloCodigo(Long codigo) {
    Optional<Produto> produto = produtoRepository.findById(codigo);

    if (!produto.isPresent()) {
      throw new EmptyResultDataAccessException(1);
    }

    return produto.get();
  }

}