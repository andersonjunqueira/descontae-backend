package br.com.iwstech.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.iwstech.util.infraestructure.domain.model.EntidadeBase;
import br.com.iwstech.util.infraestructure.domain.model.ExclusaoLogica;
import br.com.iwstech.util.infraestructure.domain.model.SimNao;

@Entity
@Table(name = "TB_CATEGORIA")
public class Categoria extends EntidadeBase<Long> implements ExclusaoLogica {

    private static final long serialVersionUID = -7014779158394975596L;

    public static final int MAX_LENGTH_NOME = 30;

    @Id
    @Column(name="ID_CATEGORIA")
    @SequenceGenerator(name="SEQ_CATEGORIA", sequenceName="SEQ_CATEGORIA", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CATEGORIA")
    private Long id;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name="EXCLUIDO", length=MAX_LENGTH_EXCLUIDO, nullable=false)
    protected SimNao excluido;

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

    @Override
    public SimNao getExcluido() {
        return excluido;
    }

    @Override
    public void setExcluido(SimNao excluido) {
        this.excluido = excluido;
    }
}
