package br.com.ertic.descontae.interfaces.web.dto;

import java.util.List;

import br.com.ertic.descontae.domain.model.ImagemUnidade;

public class HomeUnidadeDTO {

    private Long idUnidade;
    private Double valor;
    private Double desconto;
    private Double latitude;
    private Double longitude;
    private String oferta;
    private String regrasOferta;
    private String endereco;
    private String enderecoResumido;
    private List<ImagemUnidade> imagensProduto;
    private Double distancia;
    private String sobre;
    private Long notaSatisfacao;
    private Long notaPreco;

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
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

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public String getRegrasOferta() {
        return regrasOferta;
    }

    public void setRegrasOferta(String regrasOferta) {
        this.regrasOferta = regrasOferta;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnderecoResumido() {
        return enderecoResumido;
    }

    public void setEnderecoResumido(String enderecoResumido) {
        this.enderecoResumido = enderecoResumido;
    }

    public List<ImagemUnidade> getImagensProduto() {
        return imagensProduto;
    }

    public void setImagensProduto(List<ImagemUnidade> imagensProduto) {
        this.imagensProduto = imagensProduto;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public Long getNotaSatisfacao() {
        return notaSatisfacao;
    }

    public void setNotaSatisfacao(Long notaSatisfacao) {
        this.notaSatisfacao = notaSatisfacao;
    }

    public Long getNotaPreco() {
        return notaPreco;
    }

    public void setNotaPreco(Long notaPreco) {
        this.notaPreco = notaPreco;
    }

}
