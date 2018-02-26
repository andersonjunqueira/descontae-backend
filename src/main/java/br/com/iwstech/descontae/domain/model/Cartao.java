package br.com.iwstech.descontae.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.iwstech.util.infraestructure.domain.model.EntidadeBase;
import br.com.iwstech.util.infraestructure.domain.model.SituacaoAtivo;
import br.com.iwstech.util.infraestructure.domain.model.serializer.SituacaoAtivoDeserializer;
import br.com.iwstech.util.infraestructure.domain.model.serializer.SituacaoAtivoSerializer;

@Entity
@Table(name = "TB_CARTAO")
public class Cartao extends EntidadeBase<Long> {

    private static final long serialVersionUID = -3434603686449588839L;

    @Id
    @Column(name="ID_CARTAO")
    @SequenceGenerator(name="SEQ_CARTAO", sequenceName="SEQ_CARTAO", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CARTAO")
    private Long id;

    @Column(name="CODIGO", nullable=false)
    private Long codigo;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_ASSINATURA", nullable=true)
    private Assinatura assinatura;

    @ManyToOne
    @JoinColumn(name="ID_PESSOA", nullable=true)
    private Pessoa pessoa;

    @JsonSerialize(using=SituacaoAtivoSerializer.class)
    @JsonDeserialize(using=SituacaoAtivoDeserializer.class)
    @Enumerated(EnumType.STRING)
    @Column(name="ATIVO", nullable=true)
    private SituacaoAtivo ativo;

    @Transient
    private Long codigoFinal;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public SituacaoAtivo getAtivo() {
        return ativo;
    }

    public void setAtivo(SituacaoAtivo ativo) {
        this.ativo = ativo;
    }

    public Long getCodigoFinal() {
        return codigoFinal;
    }

    public void setCodigoFinal(Long codigoFinal) {
        this.codigoFinal = codigoFinal;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }


}
