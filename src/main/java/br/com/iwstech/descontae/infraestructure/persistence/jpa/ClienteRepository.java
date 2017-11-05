package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ClienteRepository extends RepositoryBase<Cliente, Long> {

}
