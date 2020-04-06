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
import br.com.labuonapasta.pedidosapi.model.Usuario;
import br.com.labuonapasta.pedidosapi.repository.UsuarioRepository;
import br.com.labuonapasta.pedidosapi.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(usuarioService.bucarPeloCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Usuario> incluir(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
        Usuario usuarioCreated = usuarioRepository.save(usuario);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioCreated.getUserId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreated);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Usuario> alterar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.alterar(codigo, usuario));
    }

    @PutMapping("/{codigo}/ativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarDesativar(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        usuarioService.ativarDesativar(codigo, ativo);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        usuarioRepository.deleteById(codigo);
    }

}