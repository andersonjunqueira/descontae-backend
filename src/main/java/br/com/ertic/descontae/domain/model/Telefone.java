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
@Table(name = "TB_TELEFONE")
public class Telefone extends EntidadeBase<Long> {

    private static final long serialVersionUID = 3087745627866627549L;

    public static final int MAX_LENGTH_IMAGEM = 200;

    @Id
    @Column(name="ID_TELEFONE")
    @SequenceGenerator(name="SEQ_TELEFONE", sequenceName="SEQ_TELEFONE", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_TELEFONE")
    private Long id;

    @Column(name="ID_PARCEIRO", nullable=true)
    private Long idParceiro;

    @Column(name="ID_PESSOA", nullable=true)
    private Long idPessoa;

    @Column(name="ID_UNIDADE", nullable=true)
    private Long idUnidade;

    @Column(name="ID_CLIENTE", nullable=true)
    private Long idCliente;

    @Column(name="NUMERO", nullable=false)
    private String numero;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(Long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


}
