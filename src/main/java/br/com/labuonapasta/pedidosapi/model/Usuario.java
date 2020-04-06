package br.com.labuonapasta.pedidosapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

  private static final long serialVersionUID = -231957467662363919L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_usuario")
  private Long userId;

  @NotBlank
  @Size(min = 3, max = 15)
  @Column(name = "nm_login")
  private String login;

  @NotBlank
  @Size(min = 3, max = 40)
  @Column(name = "nm_usuario")
  private String nomeCompleto;

  @NotBlank
  @Column(name = "ds_senha")
  private String senha;

  @Column(name = "cd_ativo")
  private boolean ativo;

  @NotNull
  @Column(name = "st_acesso")
  private AcessoType tipoAcesso;

  /**
   * @return the userId
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(final Long userId) {
    this.userId = userId;
  }

  /**
   * @return the login
   */
  public String getLogin() {
    return login;
  }

  /**
   * @param login the login to set
   */
  public void setLogin(final String login) {
    this.login = login;
  }

  /**
   * @return the nomeCompleto
   */
  public String getNomeCompleto() {
    return nomeCompleto;
  }

  /**
   * @param nomeCompleto the nomeCompleto to set
   */
  public void setNomeCompleto(final String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  /**
   * @return the senha
   */
  public String getSenha() {
    return senha;
  }

  /**
   * @param senha the senha to set
   */
  public void setSenha(final String senha) {
    this.senha = senha;
  }

  /**
   * @return the tipoAcesso
   */
  public AcessoType getTipoAcesso() {
    return tipoAcesso;
  }

  /**
   * @param tipoAcesso the tipoAcesso to set
   */
  public void setTipoAcesso(final AcessoType tipoAcesso) {
    this.tipoAcesso = tipoAcesso;
  }

  /**
   * @return the ativo
   */
  public boolean isAtivo() {
    return ativo;
  }

  /**
   * @param ativo the ativo to set
   */
  public void setAtivo(final boolean ativo) {
    this.ativo = ativo;
  }

  @Override
  public String toString() {
    return "Usuario " + getNomeCompleto() + " possui o login " + getLogin() + ". Esse usuario tem permissao de "
        + getTipoAcesso().obterDescricao() + ". Ele atualmente esta " + (isAtivo() ? "ativo." : "inativo.");
  }

  @Override
  public boolean equals(final Object obj) {
    /**
     * Se o objeto recebido for diferente de null e a sua classe for um Usuario
     * verifica igualdade no codigo do usuario.
     */
    if (obj != null && obj.getClass() == this.getClass()) {
      final Usuario user = (Usuario) obj;

      if (this.getUserId().equals(user.getUserId())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    return getLogin().hashCode();
  }

}