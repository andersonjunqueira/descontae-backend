package br.com.ertic.descontae.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_PESSOA_FISICA")
public class Pessoa extends EntidadeBase<Long> {

    private static final long serialVersionUID = -4314049694000865586L;

    public static final int MAX_LENGTH_CPF = 11;
    public static final int MAX_LENGTH_INSTAGRAM = 50;
    public static final int MAX_LENGTH_FACEBOOK = 50;
    public static final int MAX_LENGTH_TELEFONE = 15;
    public static final int MAX_LENGTH_NOME = 100;
    public static final int MAX_LENGTH_EMAIL = 100;

    @Id
    @Column(name="ID_PESSOA", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    @Column(name="EMAIL", length=MAX_LENGTH_EMAIL, nullable=false)
    private String email;

    @Column(name="CPF", length=MAX_LENGTH_CPF, nullable=false)
    private String cpf;

    @Column(name="TELEFONE", length=MAX_LENGTH_TELEFONE, nullable=true)
    private String telefone;

    @Column(name="FACEBOOK", length=MAX_LENGTH_FACEBOOK, nullable=true)
    private String facebook;

    @Column(name="INSTAGRAM", length=MAX_LENGTH_INSTAGRAM, nullable=true)
    private String instagram;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_CADASTRO", nullable=false)
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_ULTIMA_ALTERACAO", nullable=false)
    private Date dataAlteracao;

    @ManyToOne
    @JoinColumn(name="ID_TIPO_PESSOA", nullable=false)
    private TipoPessoa tipoPessoa;

    @ManyToOne
    @JoinColumn(name="ID_ENDERECO", nullable=true)
    private Endereco endereco;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
