package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Unidade;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface UnidadeRepository extends RepositoryBase<Unidade, Long> {

    @Query(value="SELECT DISTINCT o.unidade FROM OfertaUnidade o WHERE o.oferta.situacao = 'A'")
    List<Unidade> findAllByOferta(Long idOferta);

}
