package br.com.iwstech.descontae.interfaces.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.iwstech.descontae.domain.model.SituacaoAtivo;
import br.com.iwstech.descontae.domain.model.serializer.SituacaoAtivoDeserializer;
import br.com.iwstech.descontae.domain.model.serializer.SituacaoAtivoSerializer;

public class DashboardSituacaoValorDTO {

    @JsonSerialize(using=SituacaoAtivoSerializer.class)
    @JsonDeserialize(using=SituacaoAtivoDeserializer.class)
    private SituacaoAtivo situacao;
    private Long total;

    public DashboardSituacaoValorDTO() {}
    public DashboardSituacaoValorDTO(SituacaoAtivo situacao, Long total) {
        setSituacao(situacao);
        setTotal(total);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
    public SituacaoAtivo getSituacao() {
        return situacao;
    }
    public void setSituacao(SituacaoAtivo situacao) {
        this.situacao = situacao;
    }

}
