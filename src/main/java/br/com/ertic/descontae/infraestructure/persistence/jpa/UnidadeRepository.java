package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Unidade;
import br.com.ertic.descontae.interfaces.web.dto.OfertaUnidadeDTO;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface UnidadeRepository extends RepositoryBase<Unidade, Long> {

    @Query(value="SELECT DISTINCT o.unidade FROM OfertaUnidade o WHERE o.oferta.situacao = 'A'")
    List<Unidade> findAllByOferta(Long idOferta);

    @Query(value=
        "SELECT DISTINCT new br.com.ertic.descontae.interfaces.web.dto.OfertaUnidadeDTO( " +
        "       u.id, " +
        "       u.nome, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro, " +
        "       CASE WHEN (o.oferta.id = ?2) THEN 1 ELSE 0 END) " +
        "  FROM OfertaUnidade o " +
        "       JOIN o.unidade u " +
        "       JOIN u.endereco endereco " +
        " WHERE o.unidade.parceiro.marca.id = ?1")
    List<OfertaUnidadeDTO> findAllByMarcaSelecionadoPorOferta(Long idMarca, Long idOferta);

}
