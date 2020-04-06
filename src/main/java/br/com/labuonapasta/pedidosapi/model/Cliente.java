package br.com.labuonapasta.pedidosapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa o Cliente da aplicação.
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

  private static final long serialVersionUID = 5084364566310857500L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_cliente")
  private Long clieId;

  @NotBlank
  @Size(min = 3, max = 40)
  @Column(name = "nm_cliente")
  private String nome;

  @NotBlank
  @Column(name = "nr_telefone1")
  private String telefone1;

  @Column(name = "nr_telefone2")
  private String telefone2;

  @Email
  @Column(name = "ds_email")
  private String email;

  @Temporal(TemporalType.DATE)
  @Column(name = "dt_criacao")
  private Date dataCriacao;

  @NotNull
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Endereco> enderecos;

  public Long getClieId() {
    return clieId;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome.toUpperCase();
  }

  public String getTelefone1() {
    return telefone1;
  }

  public void setTelefone1(String telefone1) {
    this.telefone1 = telefone1;
  }

  public String getTelefone2() {
    if (Objects.isNull(telefone2))
      return "";

    return telefone2;
  }

  public void setTelefone2(String telefone2) {
    if (telefone2.equals(""))
      this.telefone2 = null;
    else
      this.telefone2 = telefone2;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public List<Endereco> getEnderecos() {
    if (enderecos.isEmpty()) {
      Endereco endereco = new Endereco();
      endereco.setCliente(this);
      enderecos.add(endereco);
    }

    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public void addEndereco(Endereco novoEndereco) {
    novoEndereco.setCliente(this);
    getEnderecos().add(novoEndereco);
  }

  public void removeEndereco(Endereco endereco) {
    getEnderecos().remove(endereco);
    endereco.setCliente(null);
  }

  public void removeEnderco(int index) {
    getEnderecos().remove(index).setCliente(null);
  }

  @Override
  public String toString() {
    return "O cliente " + getNome() + " é cliente desde " + getDataCriacao();
  }

  @Override
  public boolean equals(Object o) {
    // Se o objeto recebido for diferente de null e a sua classe for um Produto
    // verifica
    // igualdade no codigo do usuario.
    if (o != null && o.getClass() == this.getClass()) {
      Cliente prod = (Cliente) o;
      if (Objects.equals(prod.getClieId(), this.getClieId())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public int hashCode() {
    return getNome().hashCode();
  }

}
