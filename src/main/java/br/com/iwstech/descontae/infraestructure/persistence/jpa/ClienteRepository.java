package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ClienteRepository extends RepositoryBase<Cliente, Long> {

    @Query(value=
        "SELECT c FROM Cliente c WHERE c.pessoa.id = ?1")
    Cliente findOneByPessoa(Long idPessoa);

}
