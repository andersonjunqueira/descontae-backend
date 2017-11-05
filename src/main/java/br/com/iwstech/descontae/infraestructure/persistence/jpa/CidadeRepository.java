package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Cidade;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface CidadeRepository extends RepositoryBase<Cidade, Long> {

    @Query(value=
        "SELECT DISTINCT c " +
        "  FROM Unidade u " +
        "       JOIN u.endereco e " +
        "       JOIN e.cidade c " +
        " ORDER BY c.nome ")
    List<Cidade> findAllComParcerias();

    @Query(value=
        "SELECT DISTINCT c " +
        "  FROM Cidade c JOIN c.estado e " +
        " WHERE c.nome = ?1 AND e.sigla = ?2")
    Cidade findByNomeAndSigla(String nome, String siglaEstado);
}
