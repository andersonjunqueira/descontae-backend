package br.com.ertic.descontae.interfaces.web.dto;

import java.util.List;

import br.com.ertic.descontae.domain.model.ImagemUnidade;

public class HomeDetalheDTO {

    private Long idUnidade;
    private String marca;
    private String categoria;
    private Double valor;
    private Double desconto;
    private Double latitude;
    private Double longitude;
    private String oferta;
    private String regrasOferta;
    private List<ImagemUnidade> imagensProduto;
    private String endereco;
    private String enderecoResumido;
    private String logomarca;
    private String fundoApp;

    private String filiais;

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public List<ImagemUnidade> getImagensProduto() {
        return imagensProduto;
    }

    public void setImagensProduto(List<ImagemUnidade> imagensProduto) {
        this.imagensProduto = imagensProduto;
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

    public String getLogomarca() {
        return logomarca;
    }

    public void setLogomarca(String logomarca) {
        this.logomarca = logomarca;
    }

    public String getFiliais() {
        return filiais;
    }

    public void setFiliais(String filiais) {
        this.filiais = filiais;
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

    public String getFundoApp() {
        return fundoApp;
    }

    public void setFundoApp(String fundoApp) {
        this.fundoApp = fundoApp;
    }

}
