package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.ImagemUnidade;

@Eager
public interface ImagemUnidadeRepository extends JpaRepository<ImagemUnidade, Long> {

    List<ImagemUnidade> findByIdUnidade(Long idUnidade);
}
