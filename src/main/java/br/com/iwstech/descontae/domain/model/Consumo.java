package br.com.iwstech.descontae.domain.model;

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

import br.com.iwstech.util.infraestructure.domain.model.EntidadeBase;
import br.com.iwstech.util.infraestructure.domain.model.serializer.DataHoraDeserializer;
import br.com.iwstech.util.infraestructure.domain.model.serializer.DataHoraSerializer;

@Entity
@Table(name = "TB_CONSUMO")
public class Consumo extends EntidadeBase<Long> {

    private static final long serialVersionUID = -8063441764461188142L;

    @Id
    @Column(name="ID_CONSUMO")
    @SequenceGenerator(name="SEQ_CONSUMO", sequenceName="SEQ_CONSUMO", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CONSUMO")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_ASSINATURA", nullable=false)
    private Assinatura assinatura;

    @ManyToOne
    @JoinColumn(name="ID_OFERTA", nullable=false)
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name="ID_UNIDADE", nullable=false)
    private Unidade unidade;

    @JsonSerialize(using=DataHoraSerializer.class)
    @JsonDeserialize(using=DataHoraDeserializer.class)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATA_CONSUMO", nullable=false)
    private Date data;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
}
