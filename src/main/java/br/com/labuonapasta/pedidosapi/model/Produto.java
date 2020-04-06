package br.com.labuonapasta.pedidosapi.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.labuonapasta.pedidosapi.util.UnidadeTypeSubset;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_produto")
  private Long prodId;

  @NotBlank
  @Size(max = 50)
  @Column(name = "nm_produto")
  private String nome;

  @NotNull
  @Column(name = "st_unidade")
  @Enumerated(EnumType.STRING)
  @UnidadeTypeSubset(anyOf = { UnidadeType.KG, UnidadeType.LT, UnidadeType.UN })
  private UnidadeType unidade;

  @NotNull
  @Digits(integer = 10, fraction = 2)
  @Min(value = 0)
  @Column(name = "vl_produto")
  private BigDecimal valor;

  @NotNull
  @Column(name = "cd_tipo_produto")
  private int tipo;

  @NotNull
  @Column(name = "cd_ativo")
  private Boolean ativo;

  /**
   * @return the prodId
   */
  public Long getProdId() {
    return prodId;
  }

  /**
   * @return the nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * @param nome the nome to set
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * @return the unidade
   */
  public UnidadeType getUnidade() {
    return this.unidade;
  }

  /**
   * 
   * @param unidade a unidade para setar
   */
  public void setUnidade(UnidadeType unidade) {
    this.unidade = unidade;
  }

  /**
   * 
   * @return o valor do produto
   */
  public BigDecimal getValor() {
    return valor;
  }

  /**
   * 
   * @param valor o valor do produto para setar
   */
  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  /**
   * 
   * @return o tipo do produto
   */
  public ProdutoType getTipo() {
    return ProdutoType.parse(this.tipo);
  }

  /**
   * 
   * @param tipo o tipo do produto para setar
   */
  public void setTipo(ProdutoType tipo) {
    this.tipo = tipo.getCodigo();
  }

  /**
   * 
   * @return se o produto está ativo ou não.
   */
  public Boolean getAtivo() {
    return ativo;
  }

  /**
   * 
   * @param ativo a situação do produto para setar, se é ativo ou não-ativo.
   */
  public void setAtivo(Boolean ativo) {
    this.ativo = ativo;
  }

  @Override
  public boolean equals(Object o) {
    if (o != null && o.getClass() == this.getClass()) {
      Produto prod = (Produto) o;
      if (Objects.equals(prod.getProdId(), this.getProdId())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public int hashCode() {
    return getProdId().hashCode();
  }
}
