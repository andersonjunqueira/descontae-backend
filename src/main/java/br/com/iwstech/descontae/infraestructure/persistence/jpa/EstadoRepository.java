package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Estado;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface EstadoRepository extends RepositoryBase<Estado, Long> {

    Estado findBySigla(String sigla);

}
