package br.com.ertic.descontae.interfaces.web.dto;

import java.util.List;

public class HomeDetalheDTO {

    private Long idMarca;
    private String marca;
    private String logomarca;
    private String fundoApp;
    private String categoria;

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

    public List<HomeUnidadeDTO> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<HomeUnidadeDTO> unidades) {
        this.unidades = unidades;
    }


}
