package br.com.ertic.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_TELEFONE")
public class Telefone extends EntidadeBase<Long> {

    private static final long serialVersionUID = 3087745627866627549L;

    public static final int MAX_LENGTH_IMAGEM = 200;

    @Id
    @Column(name="ID_TELEFONE")
    @SequenceGenerator(name="SEQ_TELEFONE", sequenceName="SEQ_TELEFONE", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_TELEFONE")
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ID_PARCEIRO", nullable=true)
    private Parceiro parceiro;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ID_PESSOA", nullable=true)
    private Pessoa pessoa;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ID_UNIDADE", nullable=true)
    private Unidade unidade;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ID_CLIENTE", nullable=true)
    private Cliente cliente;

    @Column(name="NUMERO", nullable=false)
    private String numero;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
