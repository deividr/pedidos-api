package br.com.labuonapasta.pedidosapi.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Classe que irá modelar as informações que estão contidas em um item de
 * pedido.
 */
@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = -4421810074177801801L;

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_item_pedido")
    private Long numeroItem;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cd_pedido", foreignKey = @ForeignKey(name = "fk_item_pedido_pedido1"))
    private Pedido pedido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cd_produto", foreignKey = @ForeignKey(name = "fk_item_pedido_produto1"))
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "cd_molho", referencedColumnName = "cd_produto", foreignKey = @ForeignKey(name = "fk_item_pedido_produto2"))
    private Produto molho;

    @NotNull
    @DecimalMin(value = "0.001")
    @DecimalMax(value = "999999.999")
    @Column(name = "vl_quantidade")
    private BigDecimal qtde;

    public ItemPedido() {
        pedido = new Pedido();
        produto = new Produto();
    }

    public Long getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Long numeroItem) {
        this.numeroItem = numeroItem;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getMolho() {
        return molho;
    }

    public void setMolho(Produto molho) {
        this.molho = molho;
    }

    public BigDecimal getQtde() {
        return qtde;
    }

    public void setQtde(BigDecimal qtde) {
        this.qtde = qtde.multiply(new BigDecimal(1.000));
    }

    @JsonIgnore
    public BigDecimal getValorTotal() {
        return qtde.multiply(produto.getValor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ItemPedido that = (ItemPedido) o;

        return numeroItem.equals(that.numeroItem);
    }

    @Override
    public int hashCode() {
        return numeroItem.hashCode();
    }

}
