package br.com.iwstech.descontae.interfaces.web.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.iwstech.descontae.domain.model.Cidade;
import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.descontae.domain.model.Oferta;
import br.com.iwstech.descontae.domain.model.Unidade;
import br.com.iwstech.descontae.domain.model.serializer.DataDeserializer;
import br.com.iwstech.descontae.domain.model.serializer.DataSerializer;

public class ConsumoDTO {

    private Cliente cliente;

    private Cidade cidade;
    private String email;
    private Unidade unidade;
    private Oferta oferta;

    @JsonSerialize(using = DataSerializer.class)
    @JsonDeserialize(using = DataDeserializer.class)
    private Date dataInicio;

    @JsonSerialize(using = DataSerializer.class)
    @JsonDeserialize(using = DataDeserializer.class)
    private Date dataFim;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
