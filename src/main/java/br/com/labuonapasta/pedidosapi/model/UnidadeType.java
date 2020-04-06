package br.com.labuonapasta.pedidosapi.model;

public enum UnidadeType {

  UN("Unidade"), KG("Kilograma"), LT("Litros");

  private final String descricao;

  UnidadeType(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return this.descricao;
  }

  @Override
  public String toString() {
    return this.descricao;
  }

}
