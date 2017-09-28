package br.com.ertic.descontae.interfaces.web.dto;

import br.com.ertic.descontae.domain.model.Oferta;
import br.com.ertic.descontae.domain.model.Unidade;

public class ConsumoDTO {

    private String email;
    private Unidade unidade;
    private Oferta oferta;

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
