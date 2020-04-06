package br.com.labuonapasta.pedidosapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.labuonapasta.pedidosapi.event.RecursoCriadoEvent;
import br.com.labuonapasta.pedidosapi.model.Cliente;
import br.com.labuonapasta.pedidosapi.repository.ClienteRepository;
import br.com.labuonapasta.pedidosapi.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ApplicationEventPublisher publisher;

  @GetMapping
  private List<Cliente> listar() {
    return clienteRepository.findAll();
  }

  @GetMapping("/{codigo}")
  private Cliente buscarPeloCodigo(@PathVariable Long codigo) {
    return clienteService.buscarPorCodigo(codigo);
  }

  @PostMapping
  private Cliente incluir(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
    Cliente clienteCreated = clienteRepository.save(cliente);

    publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteCreated.getClieId()));

    return clienteCreated;
  }

}