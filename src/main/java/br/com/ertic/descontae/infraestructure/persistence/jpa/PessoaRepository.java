package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Pessoa;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface PessoaRepository extends RepositoryBase<Pessoa, Long> {

    @Query(value="SELECT COUNT(*) FROM Pessoa p WHERE p.email = ?1 OR p.cpf = ?1")
    Long checkDuplicate(String cpfOuEmail);

}
