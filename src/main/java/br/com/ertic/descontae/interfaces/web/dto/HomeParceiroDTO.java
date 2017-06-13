package br.com.ertic.descontae.interfaces.web.dto;

public class HomeParceiroDTO {

    private Long idMarca;
    private String marca;
    private Double distanciaKM;
    private String imagem;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(Double distanciaKM) {
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

}
