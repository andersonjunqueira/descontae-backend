package br.com.iwstech.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.iwstech.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_TIPO_PESSOA")
public class TipoPessoa extends EntidadeBase<Long> {

    private static final long serialVersionUID = 4010241792268709045L;

    public static final int MAX_LENGTH_NOME = 30;

    public static final TipoPessoa TIPO_ADMINISTRADOR = new TipoPessoa(1L);
    public static final TipoPessoa TIPO_PARCEIRO = new TipoPessoa(2L);
    public static final TipoPessoa TIPO_CLIENTE = new TipoPessoa(3L);
    public static final TipoPessoa TIPO_USUARIO = new TipoPessoa(4L);

    @Id
    @Column(name="ID_TIPO_PESSOA")
    private Long id;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    public TipoPessoa() {}

    public TipoPessoa(Long id) {
        setId(id);
    }

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


}
