package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.ImagemUnidade;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ImagemUnidadeRepository extends RepositoryBase<ImagemUnidade, Long> {

    @Query(value=
        "SELECT i " +
        "  FROM ImagemUnidade i " +
        " WHERE i.unidade.id  = ?1 ")
    List<ImagemUnidade> findByIdUnidade(Long idUnidade);
}
