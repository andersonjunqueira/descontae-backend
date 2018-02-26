package br.com.iwstech.descontae.interfaces.web.dto;

public class DashboardChaveValorDTO {

    private String nome;
    private Long total;
    private String percentual;

    public DashboardChaveValorDTO() {
    }

    public DashboardChaveValorDTO(String nome, Long total) {
        setNome(nome);
        setTotal(total);
    }

    public DashboardChaveValorDTO(String nome, Long total, String percentual) {
        setNome(nome);
        setTotal(total);
        setPercentual(percentual);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getPercentual() {
        return percentual;
    }

    public void setPercentual(String percentual) {
        this.percentual = percentual;
    }

}
//