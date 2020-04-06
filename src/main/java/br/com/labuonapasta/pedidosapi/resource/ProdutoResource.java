package br.com.labuonapasta.pedidosapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.labuonapasta.pedidosapi.event.RecursoCriadoEvent;
import br.com.labuonapasta.pedidosapi.model.Produto;
import br.com.labuonapasta.pedidosapi.repository.ProdutoRepository;
import br.com.labuonapasta.pedidosapi.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  private List<Produto> getAll() {
    return produtoRepository.findAll();
  }

  @GetMapping("/{codigo}")
  private ResponseEntity<Produto> buscarPorCodigo(@PathVariable Long codigo) {
    return ResponseEntity.ok(produtoService.buscarPeloCodigo(codigo));
  }

  @PostMapping
  private ResponseEntity<Produto> incluir(@Valid @RequestBody Produto produto, HttpServletResponse response) {
    Produto produtoCreated = produtoRepository.save(produto);

    publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoCreated.getProdId()));

    return ResponseEntity.status(HttpStatus.CREATED).body(produtoCreated);
  }

  @PutMapping("/{codigo}")
  private ResponseEntity<Produto> alterar(@PathVariable Long codigo, @Valid @RequestBody Produto produto) {
    return ResponseEntity.ok(produtoService.alterar(codigo, produto));
  }

  @DeleteMapping("/{codigo}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  private void deletar(@PathVariable Long codigo) {
    produtoRepository.deleteById(codigo);
  }

}