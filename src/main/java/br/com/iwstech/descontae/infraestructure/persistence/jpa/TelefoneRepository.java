package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Telefone;
import br.com.iwstech.descontae.domain.model.Unidade;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface TelefoneRepository extends RepositoryBase<Telefone, Long> {

    List<Telefone> findByUnidade(Unidade unidade);
}
