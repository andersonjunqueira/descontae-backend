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
@Table(name = "TB_AVALIACAO")
public class Avaliacao extends EntidadeBase<Long> {

    private static final long serialVersionUID = -3477321390404847487L;

    @Id
    @Column(name="ID_AVALIACAO", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NOTA_PRECO", nullable=true)
    private Integer preco;

    @Column(name="NOTA_SATISFACAO", nullable=true)
    private Integer satisfacao;

    @ManyToOne
    @JoinColumn(name="ID_PESSOA", nullable=false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name="ID_UNIDADE", nullable=false)
    private Pessoa unidade;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPreco() {
        return preco;
    }

    public void setPreco(Integer preco) {
        this.preco = preco;
    }

    public Integer getSatisfacao() {
        return satisfacao;
    }

    public void setSatisfacao(Integer satisfacao) {
        this.satisfacao = satisfacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getUnidade() {
        return unidade;
    }

    public void setUnidade(Pessoa unidade) {
        this.unidade = unidade;
    }

}
