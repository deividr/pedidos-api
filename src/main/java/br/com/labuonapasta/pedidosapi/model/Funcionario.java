package br.com.labuonapasta.pedidosapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Classe que representa o Funcionário da aplicação.
 */
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    private static final long serialVersionUID = -3942289873349653141L;

    @Id
    @Column(name = "nr_cpf_funcionario")
    private Long cpf;

    @Size(min = 3, max = 45)
    @Column(name = "nm_funcionario")
    private String nome;

    @Column(name = "cd_ativo")
    private boolean ativo;

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long funcId) {
        this.cpf = funcId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Funcionario that = (Funcionario) o;

        return cpf == that.cpf;
    }

    @Override
    public int hashCode() {
        return (int) (cpf ^ (cpf >>> 32));
    }
}
