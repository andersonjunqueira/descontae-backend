package br.com.ertic.descontae.interfaces.web.dto;

import java.util.Date;

public class ConsumoUsuarioDTO {

    private String parceiro;
    private String unidade;
    private String oferta;
    private Date data;

    public ConsumoUsuarioDTO() {}

    public ConsumoUsuarioDTO(String parceiro, String unidade, String oferta, Date data) {
        super();
        this.parceiro = parceiro;
        this.unidade = unidade;
        this.oferta = oferta;
        this.data = data;
    }

    public String getParceiro() {
        return parceiro;
    }

    public void setParceiro(String parceiro) {
        this.parceiro = parceiro;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
