package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Categoria;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface CategoriaRepository extends RepositoryBase<Categoria, Long> {

    @Query(value=
        "SELECT COUNT(*) " +
        "FROM Parceiro p " +
        "WHERE p.excluido = 'N' AND p.categoria.id = ?1 ")
    Long findTotalEmUso(Long idCategoria);
}
