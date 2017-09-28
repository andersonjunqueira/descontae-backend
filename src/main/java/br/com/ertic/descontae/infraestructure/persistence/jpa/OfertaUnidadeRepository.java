package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.OfertaUnidade;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface OfertaUnidadeRepository extends RepositoryBase<OfertaUnidade, Long> {

    @Query(value="SELECT ou FROM OfertaUnidade ou WHERE ou.oferta.id = ?1")
    List<OfertaUnidade> findAllByOferta(Long idOferta);

    @Query(value="SELECT ou FROM OfertaUnidade ou WHERE ou.oferta.id = ?1 AND ou.unidade.id = ?2")
    OfertaUnidade findByOfertaUnidade(Long idOferta, Long idUnidade);

}
