package br.com.iwstech.descontae.interfaces.web.dto;

public class HomeUnidadeDTO {

    private Long idUnidade;
    private String enderecoResumido;

    public Long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(Long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getEnderecoResumido() {
        return enderecoResumido;
    }

    public void setEnderecoResumido(String enderecoResumido) {
        this.enderecoResumido = enderecoResumido;
    }

}
