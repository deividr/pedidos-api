package br.com.labuonapasta.pedidosapi.exceptionhandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PedidosExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
    String mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

    List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

    return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return handleExceptionInternal(ex, criarListaErro(ex.getBindingResult()), headers, status, request);
  }

  @ExceptionHandler({ EmptyResultDataAccessException.class })
  public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
      WebRequest request) {
    String mensagemUsuario = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());

    String mensagemDesenvolvedor = ex.getMessage();

    List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));

    return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({ InvalidFormatException.class })
  public void testeInterrupcao(HttpServletResponse response, InvalidFormatException ex) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value(), "Dados da ENUM são inválidos");
  }

  public List<Erro> criarListaErro(BindingResult bindingResult) {

    List<Erro> listErro = new ArrayList<Erro>();

    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
      String mensagemDesenvolvedor = fieldError.toString();
      listErro.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
    }

    return listErro;
  }

  public static class Erro {

    private String mensagemUsuario;
    private String mensagemDesenvolvedor;

    public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
      this.mensagemDesenvolvedor = mensagemDesenvolvedor;
      this.mensagemUsuario = mensagemUsuario;
    }

    /**
     * @param mensagemDesenvolvedor the mensagemDesenvolvedor to set
     */
    public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
      this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

    /**
     * @param mensagemUsuario the mensagemUsuario to set
     */
    public void setMensagemUsuario(String mensagemUsuario) {
      this.mensagemUsuario = mensagemUsuario;
    }

    /**
     * @return the mensagemDesenvolvedor
     */
    public String getMensagemDesenvolvedor() {
      return mensagemDesenvolvedor;
    }

    /**
     * @return the mensagemUsuario
     */
    public String getMensagemUsuario() {
      return mensagemUsuario;
    }

  }

}