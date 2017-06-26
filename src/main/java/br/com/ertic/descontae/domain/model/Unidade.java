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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.ertic.util.infraestructure.domain.model.EntidadeBase;

@Entity
@Table(name = "TB_UNIDADE")
public class Unidade extends EntidadeBase<Long> {

    private static final long serialVersionUID = 5200631229042999757L;

    public static final int MAX_LENGTH_NOME = 50;
    public static final int MAX_LENGTH_SOBRE = 1000;

    @Id
    @Column(name="ID_UNIDADE")
    @SequenceGenerator(name="SEQ_UNIDADE", sequenceName="SEQ_UNIDADE", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_UNIDADE")
    private Long id;

    @Column(name="NOME", length=MAX_LENGTH_NOME, nullable=false)
    private String nome;

    @Column(name="SOBRE", length=MAX_LENGTH_SOBRE, nullable=true)
    private String sobre;

    @Column(name="ID_PARCEIRO", nullable=false)
    private Long idParceiro;

    @Temporal(TemporalType.TIME)
    @Column(name="INICIO_EXPEDIENTE", nullable=true)
    private Date inicioExpediente;

    @Temporal(TemporalType.TIME)
    @Column(name="FIM_EXPEDIENTE", nullable=true)
    private Date fimExpediente;

    @ManyToOne
    @JoinColumn(name="ID_ENDERECO", nullable=false)
    private Endereco endereco;

    @OneToMany
    @JoinColumn(name="ID_UNIDADE", referencedColumnName="ID_UNIDADE")
    private List<Avaliacao> avaliacoes;

    @OneToMany
    @JoinColumn(name="ID_UNIDADE", referencedColumnName="ID_UNIDADE")
    private List<ImagemUnidade> imagens;

    @OneToMany
    @JoinColumn(name="ID_UNIDADE", referencedColumnName="ID_UNIDADE")
    private List<Telefone> telefones;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getInicioExpediente() {
        return inicioExpediente;
    }

    public void setInicioExpediente(Date inicioExpediente) {
        this.inicioExpediente = inicioExpediente;
    }

    public Date getFimExpediente() {
        return fimExpediente;
    }

    public void setFimExpediente(Date fimExpediente) {
        this.fimExpediente = fimExpediente;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<ImagemUnidade> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemUnidade> imagens) {
        this.imagens = imagens;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Long getIdParceiro() {
        return idParceiro;
    }

    public void setIdParceiro(Long idParceiro) {
        this.idParceiro = idParceiro;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

}
