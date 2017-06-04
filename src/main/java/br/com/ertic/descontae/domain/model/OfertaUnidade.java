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
@Table(name = "TB_OFERTA_UNIDADE")
public class OfertaUnidade extends EntidadeBase<Long> {

    private static final long serialVersionUID = -3635692840951394323L;

    @Id
    @Column(name="ID_OFERTA_UNIDADE", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_OFERTA", nullable=false)
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name="ID_UNIDADE", nullable=false)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name="ID_REVISTA", nullable=false)
    private Revista revista;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
}
