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
@Table(name = "TB_ASSINATURA")
public class Assinatura extends EntidadeBase<Long> {

    private static final long serialVersionUID = 943756204957144990L;

    public static final int MAX_LENGTH_CODIGO_CARTAO = 30;

    @Id
    @Column(name="ID_ASSINATURA", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="CODIGO_CARTAO", length=MAX_LENGTH_CODIGO_CARTAO, nullable=false)
    private String codigoCartao;

    @Temporal(TemporalType.DATE)
    @Column(name="INICIO_VIGENCIA", nullable=false)
    private Date inicioVigencia;

    @Temporal(TemporalType.DATE)
    @Column(name="FIM_VIGENCIA", nullable=true)
    private Date fimVigencia;

    @ManyToOne
    @JoinColumn(name="ID_CARTAO", nullable=false)
    private Cartao cartao;

    @ManyToOne
    @JoinColumn(name="ID_PLANO", nullable=false)
    private Plano plano;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCartao() {
        return codigoCartao;
    }

    public void setCodigoCartao(String codigoCartao) {
        this.codigoCartao = codigoCartao;
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

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }


}
