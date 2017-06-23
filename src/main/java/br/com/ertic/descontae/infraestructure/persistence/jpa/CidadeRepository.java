package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Cidade;

@Eager
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query(value=
        "SELECT DISTINCT c " +
        "  FROM Unidade u " +
        "       JOIN u.endereco e " +
        "       JOIN e.cidade c " +
        " ORDER BY c.nome ")
    List<Cidade> findAllComParcerias();
}
