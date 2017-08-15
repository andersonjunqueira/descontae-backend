package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.ImagemUnidade;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ImagemUnidadeRepository extends RepositoryBase<ImagemUnidade, Long> {

    List<ImagemUnidade> findByIdUnidade(Long idUnidade);
}
