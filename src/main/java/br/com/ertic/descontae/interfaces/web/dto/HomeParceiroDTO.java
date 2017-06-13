package br.com.ertic.descontae.interfaces.web.dto;

import java.util.Date;

public class HomeParceiroDTO {

    private Long idUnidade;
    private Long idMarca;
    private String marca;
    private String categoria;
    private Double distanciaKM;
    private Long nota;
    private Date horaAbrir;
    private Date horaFechar;
    private String imagem;

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

    public Double getDistanciaKM() {
        return distanciaKM;
    }

    public void setDistanciaKM(Double distanciaKM) {
        this.distanciaKM = distanciaKM;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    public Date getHoraAbrir() {
        return horaAbrir;
    }

    public void setHoraAbrir(Date horaAbrir) {
        this.horaAbrir = horaAbrir;
    }

    public Date getHoraFechar() {
        return horaFechar;
    }

    public void setHoraFechar(Date horaFechar) {
        this.horaFechar = horaFechar;
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
