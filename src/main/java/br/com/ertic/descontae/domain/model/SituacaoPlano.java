package br.com.ertic.descontae.domain.model;

public enum SituacaoPlano {

    A ("A", "Ativo"),
    I ("I", "Inativo");

    private String sigla;
    private String nome;

    private SituacaoPlano (String sigla, String nome) {
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }

}
