package br.com.iwstech.descontae.interfaces.web.dto;

import java.util.List;

import br.com.iwstech.descontae.domain.model.Cliente;

public class DashboardConsumosDTO {

    private Cliente cliente;
    private List<DashboardSituacaoValorDTO> cartoesAtivos;
    private List<DashboardChaveValorDTO> totaisByCategoria;
    private List<DashboardChaveValorDTO> totaisByCidade;
    private List<DashboardChaveValorDTO> totaisByBairro;
    private Long totais;

    public List<DashboardSituacaoValorDTO> getCartoesAtivos() {
        return cartoesAtivos;
    }

    public void setCartoesAtivos(List<DashboardSituacaoValorDTO> cartoesAtivos) {
        this.cartoesAtivos = cartoesAtivos;
    }

    public List<DashboardChaveValorDTO> getTotaisByCategoria() {
        return totaisByCategoria;
    }

    public void setTotaisByCategoria(
        List<DashboardChaveValorDTO> totaisByCategoria) {
        this.totaisByCategoria = totaisByCategoria;
    }

    public List<DashboardChaveValorDTO> getTotaisByCidade() {
        return totaisByCidade;
    }

    public void setTotaisByCidade(List<DashboardChaveValorDTO> totaisByCidade) {
        this.totaisByCidade = totaisByCidade;
    }

    public List<DashboardChaveValorDTO> getTotaisByBairro() {
        return totaisByBairro;
    }

    public void setTotaisByBairro(List<DashboardChaveValorDTO> totaisByBairro) {
        this.totaisByBairro = totaisByBairro;
    }

    public Long getTotais() {
        return totais;
    }

    public void setTotais(Long totais) {
        this.totais = totais;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
