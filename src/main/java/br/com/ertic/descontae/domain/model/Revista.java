package br.com.ertic.descontae.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
    public static final int MAX_LENGTH_IMAGEM = 200;

    @Id
    @Column(name="ID_REVISTA")
    @SequenceGenerator(name="SEQ_REVISTA", sequenceName="SEQ_REVISTA", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_REVISTA")
    private Long id;

    @Column(name="EDICAO", length=MAX_LENGTH_EDICAO,  nullable=false)
    private String edicao;

    @Column(name="DESCRICAO", length=MAX_LENGTH_DESCRICAO,  nullable=false)
    private String descricao;

    @Temporal(TemporalType.DATE)
    @Column(name="INICIO_VIGENCIA", nullable=false)
    private Date inicioVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name="FIM_VIGENCIA", nullable=false)
    private Date fimVigencia;

    @Column(name="PDF", length=MAX_LENGTH_PDF,  nullable=false)
    private String pdf;

    @Column(name="IMAGEM", length=MAX_LENGTH_IMAGEM,  nullable=false)
    private String imagem;

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

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
