package br.com.iwstech.descontae.interfaces.web.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.iwstech.descontae.domain.model.serializer.DataDeserializer;
import br.com.iwstech.descontae.domain.model.serializer.DataSerializer;

public class ConsumoListDTO {

    private Long consumoId;
    private Long numeroCartao;
    private String bairro;
    private String cidade;
    private String uf;
    private String nomeUsuario;
    private String marca;
    private String oferta;

    @JsonSerialize(using = DataSerializer.class)
    @JsonDeserialize(using = DataDeserializer.class)
    private Date data;

    public ConsumoListDTO() {
    }

    public ConsumoListDTO(Long consumoId, Long numeroCartao, String cidade,
        String uf, String bairro, String nomeUsuario, String marca,
        String oferta, Date data) {

        setConsumoId(consumoId);
        setNumeroCartao(numeroCartao);
        setCidade(cidade);
        setBairro(bairro);
        setUf(uf);
        setNomeUsuario(nomeUsuario);
        setMarca(marca);
        setOferta(oferta);
        setData(data);
    }

    public Long getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(Long numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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

    public Long getConsumoId() {
        return consumoId;
    }

    public void setConsumoId(Long consumoId) {
        this.consumoId = consumoId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
