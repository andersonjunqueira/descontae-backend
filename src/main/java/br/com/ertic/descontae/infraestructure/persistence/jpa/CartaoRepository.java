package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Cartao;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface CartaoRepository extends RepositoryBase<Cartao, Long> {

    @Query(value="SELECT c.id FROM Cartao c WHERE c.codigo = ?1")
    Long findByCodigo(Long codigo);

    //TODO COLOCAR VERIFICACAO DE ASSINATURA PARA VALIDAR O CARTAO
    @Query(value="SELECT c FROM Cartao c LEFT JOIN c.assinatura a WHERE a.pessoa.email = ?1 AND c.ativo = 'S'")
    Cartao findAtivoByUsuario(String email);

}
