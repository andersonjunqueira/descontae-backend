package br.com.ertic.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_MARCA_FRANQUIA")
public class MarcaFranquia extends EntidadeBase<Long> {

    private static final long serialVersionUID = 4010241792268709045L;

    public static final int MAX_LENGTH_NOME = 100;
    public static final int MAX_LENGTH_LOGO = 200;

    @Id
    @Column(name="ID_MARCA_FRANQUIA")
    @GeneratedValue
    private Long id;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    @Column(name="LOGO", length=MAX_LENGTH_LOGO, nullable=false)
    private String caminhoLogomarca;

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

    public String getCaminhoLogomarca() {
        return caminhoLogomarca;
    }

    public void setCaminhoLogomarca(String caminhoLogomarca) {
        this.caminhoLogomarca = caminhoLogomarca;
    }


}
