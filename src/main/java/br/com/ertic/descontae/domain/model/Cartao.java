package br.com.ertic.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_CARTAO")
public class Cartao extends EntidadeBase<Long> {

    private static final long serialVersionUID = -3434603686449588839L;

    public static final int MAX_LENGTH_CODIGO = 50;

    @Id
    @Column(name="ID_CARTAO", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="CODIGO", length=MAX_LENGTH_CODIGO, nullable=false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name="ID_PESSOA", nullable=true)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name="ID_CLIENTE", nullable=true)
    private Cliente cliente;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


}
