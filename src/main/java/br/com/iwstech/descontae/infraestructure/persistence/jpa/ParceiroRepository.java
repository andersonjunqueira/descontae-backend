package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Parceiro;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ParceiroRepository extends RepositoryBase<Parceiro, Long> {

    @Query(value=
        "SELECT COUNT(*) " +
        "FROM Parceiro p " +
        "WHERE p.excluido = 'N' AND p.categoria.id = ?1 ")
    Long findTotalEmUso(Long idCategoria);

}
