package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.iwstech.descontae.domain.model.Consumo;
import br.com.iwstech.descontae.domain.model.SituacaoAtivo;

public interface OfertaCustomRepository {

    Page<Object[]> findListaSimples(String texto, SituacaoAtivo situacao, Pageable pageable);
    public Consumo findUltimoConsumo(long idOferta, long idPessoa);

}
