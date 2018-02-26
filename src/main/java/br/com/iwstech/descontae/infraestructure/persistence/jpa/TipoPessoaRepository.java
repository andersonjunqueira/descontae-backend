package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.TipoPessoa;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface TipoPessoaRepository extends RepositoryBase<TipoPessoa, Long> {

}
