package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ertic.descontae.domain.model.SituacaoOferta;

public interface OfertaCustomRepository {

    Page<Object[]> findListaSimples(String texto, SituacaoOferta situacao, Pageable pageable);

}
