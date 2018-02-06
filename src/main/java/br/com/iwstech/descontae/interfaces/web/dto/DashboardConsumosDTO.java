package br.com.iwstech.descontae.interfaces.web.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.iwstech.descontae.domain.model.Cidade;
import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.util.infraestructure.domain.model.serializer.DataDeserializer;
import br.com.iwstech.util.infraestructure.domain.model.serializer.DataSerializer;

public class DashboardConsumosDTO {

    private Long cartoesTotais;
    private List<DashboardSituacaoValorDTO> cartoesAtivos;
    private Long consumosTotais;
    private List<DashboardChaveValorDTO> consumosTotaisByCategoria;
    private List<DashboardChaveValorDTO> consumosTotaisByCidade;
    private List<DashboardChaveValorDTO> consumosTotaisByBairro;

    private Cidade cidade;
    private Cliente cliente;

    @JsonSerialize(using=DataSerializer.class)
    @JsonDeserialize(using=DataDeserializer.class)
    private Date dataInicio;

    @JsonSerialize(using=DataSerializer.class)
    @JsonDeserialize(using=DataDeserializer.class)
    private Date dataFim;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getCartoesTotais() {
        return cartoesTotais;
    }

    public void setCartoesTotais(Long cartoesTotais) {
        this.cartoesTotais = cartoesTotais;
    }

    public List<DashboardSituacaoValorDTO> getCartoesAtivos() {
        return cartoesAtivos;
    }

    public void setCartoesAtivos(
        List<DashboardSituacaoValorDTO> cartoesAtivos) {
        this.cartoesAtivos = cartoesAtivos;
    }

    public Long getConsumosTotais() {
        return consumosTotais;
    }

    public void setConsumosTotais(Long consumosTotais) {
        this.consumosTotais = consumosTotais;
    }

    public List<DashboardChaveValorDTO> getConsumosTotaisByCategoria() {
        return consumosTotaisByCategoria;
    }

    public void setConsumosTotaisByCategoria(
        List<DashboardChaveValorDTO> consumosTotaisByCategoria) {
        this.consumosTotaisByCategoria = consumosTotaisByCategoria;
    }

    public List<DashboardChaveValorDTO> getConsumosTotaisByCidade() {
        return consumosTotaisByCidade;
    }

    public void setConsumosTotaisByCidade(
        List<DashboardChaveValorDTO> consumosTotaisByCidade) {
        this.consumosTotaisByCidade = consumosTotaisByCidade;
    }

    public List<DashboardChaveValorDTO> getConsumosTotaisByBairro() {
        return consumosTotaisByBairro;
    }

    public void setConsumosTotaisByBairro(
        List<DashboardChaveValorDTO> consumosTotaisByBairro) {
        this.consumosTotaisByBairro = consumosTotaisByBairro;
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
