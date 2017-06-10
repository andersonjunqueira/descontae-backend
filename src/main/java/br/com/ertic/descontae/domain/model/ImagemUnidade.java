package br.com.ertic.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_IMAGEM")
public class ImagemUnidade extends EntidadeBase<Long> {

    private static final long serialVersionUID = 3087745627866627549L;

    public static final int MAX_LENGTH_IMAGEM = 200;

    @Id
    @Column(name="ID_IMAGEM", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="IMAGEM", length=MAX_LENGTH_IMAGEM, nullable=false)
    private String caminho;

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

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

}
