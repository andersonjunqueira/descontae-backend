package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Oferta;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface OfertaRepository extends RepositoryBase<Oferta, Long> {

    @Query(value="SELECT ou.oferta FROM OfertaUnidade ou WHERE ou.unidade.id = ?1 AND ou.oferta.situacao = 'A'")
    List<Oferta> findAllAtivasByUnidade(Long idUnidade);

}
