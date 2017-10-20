package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.TipoPessoa;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface TipoPessoaRepository extends RepositoryBase<TipoPessoa, Long> {

}
