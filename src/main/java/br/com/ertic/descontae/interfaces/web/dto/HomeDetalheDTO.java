package br.com.ertic.descontae.interfaces.web.dto;

import java.util.List;

import br.com.ertic.descontae.domain.model.ImagemUnidade;
import br.com.ertic.descontae.domain.model.Oferta;

public class HomeDetalheDTO {

    private Long idMarca;
    private Long idUnidade;
    private String marca;
    private String logomarca;
    private String fundoApp;
    private String categoria;
    private Double latitude;
    private Double longitude;
    private String endereco;
    private String enderecoResumido;
    private Double distancia;
    private String sobre;
    private String horaAbrir;
    private String horaFechar;
    private Double curtidas;
    private Double descurtidas;

    private List<Oferta> ofertas;
    private List<ImagemUnidade> imagensProduto;
    private List<HomeUnidadeDTO> unidades;

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLogomarca() {
        return logomarca;
    }

    public void setLogomarca(String logomarca) {
        this.logomarca = logomarca;
    }

    public String getFundoApp() {
        return fundoApp;
    }

    public void setFundoApp(String fundoApp) {
        this.fundoApp = fundoApp;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
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

    public List<HomeUnidadeDTO> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<HomeUnidadeDTO> unidades) {
        this.unidades = unidades;
    }

    public String getHoraAbrir() {
        return horaAbrir;
    }

    public void setHoraAbrir(String horaAbrir) {
        this.horaAbrir = horaAbrir;
    }

    public String getHoraFechar() {
        return horaFechar;
    }

    public void setHoraFechar(String horaFechar) {
        this.horaFechar = horaFechar;
    }

    public Double getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(Double curtidas) {
        this.curtidas = curtidas;
    }

    public Double getDescurtidas() {
        return descurtidas;
    }

    public void setDescurtidas(Double descurtidas) {
        this.descurtidas = descurtidas;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }


}
