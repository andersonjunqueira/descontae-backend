package br.com.ertic.descontae.interfaces.web.dto;

import br.com.ertic.descontae.domain.model.Categoria;
import br.com.ertic.descontae.domain.model.MarcaFranquia;
import br.com.ertic.descontae.domain.model.Unidade;

public class ListaEmpreendimentoDTO {

    private Unidade unidade;
    private MarcaFranquia marca;
    private Categoria categoria;

    public ListaEmpreendimentoDTO() {
    }

    public ListaEmpreendimentoDTO(Unidade unidade, MarcaFranquia marca, Categoria categoria) {
        setUnidade(unidade);
        setMarca(marca);
        setCategoria(categoria);
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public MarcaFranquia getMarca() {
        return marca;
    }

    public void setMarca(MarcaFranquia marca) {
        this.marca = marca;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
