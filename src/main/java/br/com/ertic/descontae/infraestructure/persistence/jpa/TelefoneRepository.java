package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Telefone;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface TelefoneRepository extends RepositoryBase<Telefone, Long> {

}
