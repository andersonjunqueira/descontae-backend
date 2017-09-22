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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ertic.descontae.domain.model.serializer.SituacaoAtivoDeserializer;
import br.com.ertic.descontae.domain.model.serializer.SituacaoAtivoSerializer;
import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_CARTAO")
public class Cartao extends EntidadeBase<Long> {

    private static final long serialVersionUID = -3434603686449588839L;

    public static final int MAX_LENGTH_CODIGO = 50;

    @Id
    @Column(name="ID_CARTAO")
    @SequenceGenerator(name="SEQ_CARTAO", sequenceName="SEQ_CARTAO", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CARTAO")
    private Long id;

    @Column(name="CODIGO", length=MAX_LENGTH_CODIGO, nullable=false)
    private String codigo;

    @ManyToOne
    @JoinColumn(name="ID_ASSINATURA", nullable=true)
    private Assinatura assinatura;

    @ManyToOne
    @JoinColumn(name="ID_CLIENTE", nullable=true)
    private Cliente cliente;

    @JsonSerialize(using=SituacaoAtivoSerializer.class)
    @JsonDeserialize(using=SituacaoAtivoDeserializer.class)
    @Enumerated(EnumType.STRING)
    @Column(name="ATIVO", nullable=true)
    private SituacaoAtivo ativo;

    @Transient
    private String codigoFinal;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public SituacaoAtivo getAtivo() {
        return ativo;
    }

    public void setAtivo(SituacaoAtivo ativo) {
        this.ativo = ativo;
    }

    public String getCodigoFinal() {
        return codigoFinal;
    }

    public void setCodigoFinal(String codigoFinal) {
        this.codigoFinal = codigoFinal;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }


}
