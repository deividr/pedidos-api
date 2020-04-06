package br.com.labuonapasta.pedidosapi.model;

public enum ProdutoType {

  MASSA("Massa", 1), MOLHO("Molho", 2), BEBIDA("Bebida", 3), SALADA("Salada", 4), CARNE("Carne", 5),
  DIVERSOS("Diversos", 99);

  private final String descricao;
  private final int codigo;

  ProdutoType(String descricao, int codigo) {
    this.descricao = descricao;
    this.codigo = codigo;
  }

  public String getDescricao() {
    return this.descricao;
  }

  public int getCodigo() {
    return this.codigo;
  }

  public static ProdutoType parse(int codigo) {
    for (ProdutoType prodEnum : ProdutoType.values()) {
      if (prodEnum.getCodigo() == codigo) {
        return prodEnum;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return this.descricao;
  }

}
