package br.com.ertic.descontae.interfaces.web.dto;

public class OfertaUnidadeDTO {

    private Long id;
    private String nome;
    private String endereco;
    private int selecionada;

    public OfertaUnidadeDTO() {}

    public OfertaUnidadeDTO(Long id, String nome, String endereco, int selecionada) {
        setId(id);
        setNome(nome);
        setEndereco(endereco);
        setSelecionada(selecionada);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int isSelecionada() {
        return selecionada;
    }

    public void setSelecionada(int selecionada) {
        this.selecionada = selecionada;
    }

}
