package br.com.iwstech.descontae.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.iwstech.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_CONFIGURACAO")
public class Configuracao extends EntidadeBase<Long> {

    private static final long serialVersionUID = -2964776867264618232L;

    public static final Configuracao CONF_ESCONDE_CADASTRO_COMPLETO = new Configuracao(1L);

    @Id
    @Column(name="ID_CONFIGURACAO")
    private Long id;

    @Column(name="CHAVE", nullable=false)
    private String chave;

    @Column(name="VALOR", nullable=false)
    private String valor;

    public Configuracao() {}
    public Configuracao(Long id) {
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

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
