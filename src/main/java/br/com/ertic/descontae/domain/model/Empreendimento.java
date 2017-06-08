package br.com.ertic.descontae.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_EMPREENDIMENTO")
public class Empreendimento extends EntidadeBase<Long> {

    private static final long serialVersionUID = 239405336985899452L;

    public static final int MAX_LENGTH_CNPJ = 14;
    public static final int MAX_LENGTH_NOME = 100;
    public static final int MAX_LENGTH_NOME_FANTASIA = 100;
    public static final int MAX_LENGTH_EMAIL = 100;

    @Id
    @Column(name="ID_EMPREENDIMENTO", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    @Column(name="NOME_FANTASIA", length=MAX_LENGTH_NOME_FANTASIA, nullable=false)
    private String nomeFantasia;

    @Column(name="EMAIL", length=MAX_LENGTH_EMAIL, nullable=true)
    private String email;

    @Column(name="CNPJ", length=MAX_LENGTH_CNPJ, nullable=false)
    private String cnpj;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_CADASTRO", nullable=false)
    private Date dataCadastro;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_ULTIMA_ATUALIZACAO", nullable=false)
    private Date dataAlteracao;

    @ManyToOne
    @JoinColumn(name="ID_PESSOA", nullable=false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name="ID_CATEGORIA", nullable=false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="ID_MARCA_FRANQUIA", nullable=false)
    private MarcaFranquia marca;

    @OneToMany
    @JoinColumn(name="ID_EMPREENDIMENTO", referencedColumnName="ID_EMPREENDIMENTO")
    private List<Unidade> unidades;

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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public MarcaFranquia getMarca() {
        return marca;
    }

    public void setMarca(MarcaFranquia marca) {
        this.marca = marca;
    }

}
