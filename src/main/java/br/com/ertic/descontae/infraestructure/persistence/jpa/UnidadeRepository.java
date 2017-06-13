package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Unidade;

@Eager
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

}
