package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Unidade;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface UnidadeRepository extends RepositoryBase<Unidade, Long> {

    @Query(value="SELECT DISTINCT o.unidade FROM OfertaUnidade o WHERE o.oferta.situacao = 'A' AND o.oferta.id = ?1")
    List<Unidade> findAllByOferta(Long idOferta);

    @Query(value="SELECT u FROM Unidade u WHERE u.parceiro.marca.id = ?1 ORDER BY u.parceiro.marca.nome")
    List<Unidade> findAllByMarca(Long idMarca);

}
