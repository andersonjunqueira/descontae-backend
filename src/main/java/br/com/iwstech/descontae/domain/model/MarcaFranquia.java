package br.com.iwstech.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.iwstech.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_MARCA_FRANQUIA")
public class MarcaFranquia extends EntidadeBase<Long> {

    private static final long serialVersionUID = 4010241792268709045L;

    public static final int MAX_LENGTH_NOME = 100;
    public static final int MAX_LENGTH_IMAGEM = 200;

    @Id
    @Column(name="ID_MARCA_FRANQUIA")
    @SequenceGenerator(name="SEQ_MARCA_FRANQUIA", sequenceName="SEQ_MARCA_FRANQUIA", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_MARCA_FRANQUIA")
    private Long id;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    @Column(name="IMAGEM_LOGO", length=MAX_LENGTH_IMAGEM, nullable=false)
    private String logomarca;

    @Column(name="IMAGEM_FUNDO_APP", length=MAX_LENGTH_IMAGEM, nullable=false)
    private String imagemFundoApp;

    @Column(name="IMAGEM_THUMBNAIL", length=MAX_LENGTH_IMAGEM, nullable=false)
    private String imagemThumbnail;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogomarca() {
        return logomarca;
    }

    public void setLogomarca(String logomarca) {
        this.logomarca = logomarca;
    }

    public String getImagemFundoApp() {
        return imagemFundoApp;
    }

    public void setImagemFundoApp(String imagemFundoApp) {
        this.imagemFundoApp = imagemFundoApp;
    }

    public String getImagemThumbnail() {
        return imagemThumbnail;
    }

    public void setImagemThumbnail(String imagemThumbnail) {
        this.imagemThumbnail = imagemThumbnail;
    }


}
