package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Oferta;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface OfertaRepository extends RepositoryBase<Oferta, Long> {

    @Query(value="SELECT DISTINCT o.oferta FROM OfertaUnidade o WHERE o.revista.id = ?1 AND o.oferta.situacao = 'A'")
    List<Oferta> findAllByRevista(Long idRevista);

}
