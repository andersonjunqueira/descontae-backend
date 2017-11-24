package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Categoria;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface CategoriaRepository extends RepositoryBase<Categoria, Long> {

    @Query(value=
        "SELECT COUNT(*) " +
        "  FROM Unidade u " +
        "       INNER JOIN u.parceiro p, " +
        "       OfertaUnidade ou " +
        " WHERE p.categoria.id = ?1 AND ou.unidade = u AND p.excluido = 'N' ")
    Long findTotalEmUso(Long idCategoria);
}
