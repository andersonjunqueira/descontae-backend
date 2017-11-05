package br.com.iwstech.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.iwstech.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_IMAGEM")
public class ImagemUnidade extends EntidadeBase<Long> {

    private static final long serialVersionUID = 3087745627866627549L;

    @Id
    @Column(name="ID_IMAGEM")
    @SequenceGenerator(name="SEQ_IMAGEM", sequenceName="SEQ_IMAGEM", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_IMAGEM")
    private Long id;

    @Column(name="IMAGEM", nullable=false)
    private String imagem;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="ID_UNIDADE", nullable=false)
    private Unidade unidade;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

}
