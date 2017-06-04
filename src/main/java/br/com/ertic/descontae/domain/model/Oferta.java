package br.com.ertic.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_OFERTA")
public class Oferta extends EntidadeBase<Long> {

    private static final long serialVersionUID = 7747639292420086763L;

    public static final int MAX_LENGTH_DESCRICAO = 500;
    public static final int MAX_LENGTH_IMAGEM = 200;

    @Id
    @Column(name="ID_OFERTA", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="DESCRICAO", length=MAX_LENGTH_DESCRICAO, nullable=false)
    private String descricao;

    @Column(name="IMAGEM", length=MAX_LENGTH_IMAGEM, nullable=true)
    private String imagem;

    @Column(name="VALOR", nullable=true)
    private Double valor;

    @Column(name="DESCONTO", nullable=true)
    private Double desconto;

    @ManyToOne
    @JoinColumn(name="ID_PESSOA", nullable=false)
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    @Column(name="SITUACAO", nullable=false)
    private SituacaoPlano situacao;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public SituacaoPlano getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoPlano situacao) {
        this.situacao = situacao;
    }

}
