package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Consumo;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ConsumoRepository extends RepositoryBase<Consumo, Long> {

}
