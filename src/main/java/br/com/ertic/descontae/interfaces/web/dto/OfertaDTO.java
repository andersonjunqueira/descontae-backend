package br.com.ertic.descontae.interfaces.web.dto;

import java.util.List;

import br.com.ertic.descontae.domain.model.Pessoa;
import br.com.ertic.descontae.domain.model.SituacaoOferta;

public class OfertaDTO {

    private Long id;
    private String descricao;
    private String imagem;
    private Double valor;
    private Double desconto;
    private Pessoa pessoa;
    private SituacaoOferta situacao;
    private String regras;

    private List<OfertaUnidadeDTO> unidades;
    private Long marcaSelecionada;

    public List<OfertaUnidadeDTO> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<OfertaUnidadeDTO> unidades) {
        this.unidades = unidades;
    }

    public Long getMarcaSelecionada() {
        return marcaSelecionada;
    }

    public void setMarcaSelecionada(Long marcaSelecionada) {
        this.marcaSelecionada = marcaSelecionada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public SituacaoOferta getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoOferta situacao) {
        this.situacao = situacao;
    }

    public String getRegras() {
        return regras;
    }

    public void setRegras(String regras) {
        this.regras = regras;
    }

}
