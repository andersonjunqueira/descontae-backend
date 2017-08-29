package br.com.ertic.descontae.interfaces.web.dto;

import java.util.List;

import br.com.ertic.descontae.domain.model.MarcaFranquia;
import br.com.ertic.descontae.domain.model.Oferta;
import br.com.ertic.descontae.domain.model.Unidade;

public class OfertaDTO {

    private Oferta oferta;
    private MarcaFranquia marca;
    private List<Unidade> unidades;

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public MarcaFranquia getMarca() {
        return marca;
    }

    public void setMarca(MarcaFranquia marca) {
        this.marca = marca;
    }

    public List<Unidade> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidade> unidades) {
        this.unidades = unidades;
    }

}
