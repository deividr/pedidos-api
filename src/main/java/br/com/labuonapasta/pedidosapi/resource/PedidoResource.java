package br.com.labuonapasta.pedidosapi.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.labuonapasta.pedidosapi.event.RecursoCriadoEvent;
import br.com.labuonapasta.pedidosapi.model.Pedido;
import br.com.labuonapasta.pedidosapi.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("pedidos")
public class PedidoResource {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> getById(@PathVariable Long codigo) {
        Optional<Pedido> pedido = pedidoRepository.findById(codigo);

        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> incluir(@Valid @RequestBody Pedido pedido, HttpServletResponse response) {
        // Como temos o relacionamento bi-direcional precisamos setar o mesmo objeto
        // "pedido" para os itens.
        pedido.getItens().forEach(item -> item.setPedido(pedido));

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        publisher.publishEvent(new RecursoCriadoEvent(pedidoSalvo, response, pedidoSalvo.getPedId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
    }

}