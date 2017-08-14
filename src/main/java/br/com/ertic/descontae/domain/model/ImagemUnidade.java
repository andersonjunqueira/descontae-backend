package br.com.ertic.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

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

    @Column(name="ID_UNIDADE", nullable=false)
    private Long idUnidade;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
