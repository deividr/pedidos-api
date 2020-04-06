package br.com.labuonapasta.pedidosapi.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.labuonapasta.pedidosapi.event.RecursoCriadoEvent;

/**
 * Listener para o evento de criação de recurso.
 */
@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

  @Override
  public void onApplicationEvent(RecursoCriadoEvent event) {
    HttpServletResponse response = event.getResponse();
    Long codigo = event.getCodigo();

    // Formatar a URI que o solicitante utilizará para recuperar esse usuário
    // cadastrado
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(codigo).toUri();

    // Incluir no header de resposta a URI de recuperação do novo usuário.
    response.setHeader("Location", uri.toASCIIString());
  }
}