package br.com.ertic.descontae.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_REVISTA")
public class Revista extends EntidadeBase<Long> {

    private static final long serialVersionUID = -8865030565054694597L;

    public static final int MAX_LENGTH_EDICAO = 10;
    public static final int MAX_LENGTH_DESCRICAO = 200;
    public static final int MAX_LENGTH_PDF = 200;

    @Id
    @Column(name="ID_REVISTA", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="EDICAO", length=MAX_LENGTH_EDICAO,  nullable=false)
    private Integer preco;

    @Column(name="DESCRICAO", length=MAX_LENGTH_DESCRICAO,  nullable=false)
    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name="INICIO_VIGENCIA", nullable=false)
    private Date inicioVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name="FIM_VIGENCIA", nullable=false)
    private Date fimVigencia;

    @Column(name="PDF", length=MAX_LENGTH_PDF,  nullable=true)
    private String pdf;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Pessoa getUnidade() {
        return unidade;
    }

    public void setUnidade(Pessoa unidade) {
        this.unidade = unidade;
    }

}
