package br.com.ertic.descontae.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.ertic.descontae.domain.model.serializer.DataDeserializer;
import br.com.ertic.descontae.domain.model.serializer.DataSerializer;
import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_ASSINATURA")
public class Assinatura extends EntidadeBase<Long> {

    private static final long serialVersionUID = 943756204957144990L;

    @Id
    @Column(name="ID_ASSINATURA")
    @SequenceGenerator(name="SEQ_ASSINATURA", sequenceName="SEQ_ASSINATURA", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_ASSINATURA")
    private Long id;

    @JsonSerialize(using=DataSerializer.class)
    @JsonDeserialize(using=DataDeserializer.class)
    @Temporal(TemporalType.DATE)
    @Column(name="INICIO_VIGENCIA", nullable=false)
    private Date inicioVigencia;

    @JsonSerialize(using=DataSerializer.class)
    @JsonDeserialize(using=DataDeserializer.class)
    @Temporal(TemporalType.DATE)
    @Column(name="FIM_VIGENCIA", nullable=true)
    private Date fimVigencia;

    @ManyToOne
    @JoinColumn(name="ID_PLANO", nullable=false)
    private Plano plano;

    @ManyToOne
    @JoinColumn(name="ID_CLIENTE", nullable=true)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="ID_PESSOA", nullable=true)
    private Pessoa pessoa;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
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
