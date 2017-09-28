package br.com.ertic.descontae.interfaces.web.dto;

import java.util.Date;

import br.com.ertic.descontae.domain.model.serializer.DataHoraDeserializer;

public class ConsumoUsuarioDTO {

    private String parceiro;
    private String unidade;
    private String oferta;
    private Date dataOriginal;
    private String data;

    public ConsumoUsuarioDTO() {}

    public ConsumoUsuarioDTO(String parceiro, String unidade, String oferta, Date dataOriginal) {
        super();
        this.parceiro = parceiro;
        this.unidade = unidade;
        this.oferta = oferta;
        this.dataOriginal = dataOriginal;
        this.data = DataHoraDeserializer.getParser().format(dataOriginal);
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
