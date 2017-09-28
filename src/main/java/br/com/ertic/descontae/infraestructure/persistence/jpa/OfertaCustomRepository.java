package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ertic.descontae.domain.model.Consumo;
import br.com.ertic.descontae.domain.model.SituacaoAtivo;

public interface OfertaCustomRepository {

    Page<Object[]> findListaSimples(String texto, SituacaoAtivo situacao, Pageable pageable);
    public Consumo findUltimoConsumo(long idOferta, long idPessoa);

}
