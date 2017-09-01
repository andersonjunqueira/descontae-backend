package br.com.ertic.descontae.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco extends EntidadeBase<Long> {

    private static final long serialVersionUID = 8119452736878543696L;

    public static final int MAX_LENGTH_CEP = 8;
    public static final int MAX_LENGTH_LOGRADOURO = 100;
    public static final int MAX_LENGTH_COMPLEMENTO = 30;
    public static final int MAX_LENGTH_NUMERO = 5;
    public static final int MAX_LENGTH_BAIRRO = 50;

    @Id
    @Column(name="ID_ENDERECO")
    @SequenceGenerator(name="SEQ_ENDERECO", sequenceName="SEQ_ENDERECO", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_ENDERECO")
    private Long id;

    @Column(name="CEP", length=MAX_LENGTH_CEP, nullable=false)
    private String cep;

    @Column(name="LOGRADOURO", length=MAX_LENGTH_LOGRADOURO, nullable=false)
    private String logradouro;

    @Column(name="COMPLEMENTO", length=MAX_LENGTH_COMPLEMENTO, nullable=true)
    private String complemento;

    @Column(name="NUMERO", length=MAX_LENGTH_NUMERO, nullable=true)
    private String numero;

    @Column(name="BAIRRO", length=MAX_LENGTH_BAIRRO, nullable=false)
    private String bairro;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_CIDADE", nullable=false)
    private Cidade cidade;

    @Column(name="LOC_LATITUDE", nullable=true)
    private Double latitude;

    @Column(name="LOC_LONGITUDE", nullable=true)
    private Double longitude;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }


}
