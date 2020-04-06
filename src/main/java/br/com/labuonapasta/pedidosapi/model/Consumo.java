package br.com.labuonapasta.pedidosapi.model;

import javax.persistence.ForeignKey;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe que representa o Consumo do Funcionário.
 */
@Entity
@Table(name = "consumo")
public class Consumo implements Serializable {

    private static final long serialVersionUID = 1482595968694927281L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_consumo")
    private Long consumoId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "nr_cpf_funcionario", foreignKey = @ForeignKey(name = "fk_consumo_funcionario1"))
    private Funcionario funcionario;

    @NotNull
    @Column(name = "dt_consumo")
    private Date dataConsumo;

    @NotNull
    @Column(name = "ds_produto")
    private String produto;

    @NotNull
    @DecimalMin(value = "0.001")
    @Column(name = "vl_produto")
    private BigDecimal valor;

    public Consumo() {
        dataConsumo = new Date();
    }

    public Long getConsumoId() {
        return consumoId;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Date getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(Date dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto.toUpperCase();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal qtde) {
        this.valor = qtde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Consumo consumo = (Consumo) o;

        return consumoId == consumo.consumoId;
    }

    @Override
    public int hashCode() {
        return consumoId.hashCode();
    }
}
