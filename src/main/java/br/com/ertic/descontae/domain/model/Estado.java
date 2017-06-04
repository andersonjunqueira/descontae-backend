package br.com.ertic.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_UF")
public class Estado extends EntidadeBase<Long> {

    private static final long serialVersionUID = 6174163472421481815L;

    public static final int MAX_LENGTH_SIGLA = 2;
    public static final int MAX_LENGTH_NOME = 30;

    @Id
    @Column(name="ID_UF")
    private Long id;

    @Column(name="SIGLA", length=MAX_LENGTH_SIGLA, nullable=false)
    private String sigla;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
