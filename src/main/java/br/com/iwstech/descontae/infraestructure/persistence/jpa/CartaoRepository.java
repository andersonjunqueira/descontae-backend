package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Cartao;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface CartaoRepository extends RepositoryBase<Cartao, Long> {

    @Query(value="SELECT c.id FROM Cartao c WHERE c.codigo = ?1")
    Long findIdByCodigo(Long codigo);

    @Query(value="SELECT c FROM Cartao c WHERE c.codigo = ?1")
    Cartao findOneByCodigo(Long codigo);

    @Query(value=
        "SELECT c " +
        "  FROM Cartao c " +
        "       LEFT JOIN c.assinatura a " +
        " WHERE c.pessoa.email = ?1 " +
        "   AND c.ativo = 'A' " +
        "   AND CURRENT_TIMESTAMP BETWEEN a.inicioVigencia AND a.fimVigencia")
    Cartao findAtivoByUsuario(String email);

}
