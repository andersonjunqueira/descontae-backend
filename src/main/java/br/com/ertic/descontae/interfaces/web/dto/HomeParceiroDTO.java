package br.com.ertic.descontae.interfaces.web.dto;

public class HomeParceiroDTO {

    private Long idMarca;
    private String marca;
    private String distanciaKM;
    private Double distancia;
    private String imagem;
    private Long idUnidade;
    private String categoria;
    private String horaAbrir;
    private String horaFechar;
    private String desconto;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(String distanciaKM) {
        this.distanciaKM = distanciaKM;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

}
