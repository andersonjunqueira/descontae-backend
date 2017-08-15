package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Cliente;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ClienteRepository extends RepositoryBase<Cliente, Long> {

}
