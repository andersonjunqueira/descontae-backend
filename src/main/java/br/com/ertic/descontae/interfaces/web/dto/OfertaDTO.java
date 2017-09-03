package br.com.ertic.descontae.interfaces.web.dto;

import java.util.List;

import br.com.ertic.descontae.domain.model.Oferta;

public class OfertaDTO {

    private Oferta oferta;
    private List<OfertaUnidadeDTO> unidades;

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public List<OfertaUnidadeDTO> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<OfertaUnidadeDTO> unidades) {
        this.unidades = unidades;
    }

}
