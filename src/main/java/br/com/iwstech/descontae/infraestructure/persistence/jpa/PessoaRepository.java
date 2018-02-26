package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Assinatura;
import br.com.iwstech.descontae.domain.model.Pessoa;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface PessoaRepository extends RepositoryBase<Pessoa, Long> {

    @Query(value="SELECT COUNT(*) FROM Pessoa p WHERE p.cpf = ?1")
    Long verificarCPFDuplicado(String cpf);

    @Query(value="SELECT COUNT(*) FROM Pessoa p WHERE p.email = ?1")
    Long verificarEmailDuplicado(String email);

    @Query(value="SELECT a FROM Cartao c JOIN c.assinatura a WHERE c.pessoa.email = ?1 AND c.ativo = 'A'")
    Assinatura findAssinaturaVigente(String email);

}
