package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ertic.descontae.interfaces.web.dto.ConsumoUsuarioDTO;

@Repository
public class ConsumoCustomRepositoryImpl implements ConsumoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public  List<ConsumoUsuarioDTO> findConsumosUsuario(Long id) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT new br.com.ertic.descontae.interfaces.web.dto.ConsumoUsuarioDTO(")
            .append("       ou.unidade.parceiro.marca.nome, ou.unidade.nome, ou.oferta.descricao, c.data) ")
            .append("  FROM Consumo c ")
            .append("       INNER JOIN c.assinatura a ")
            .append("       INNER JOIN c.ofertaUnidade ou, ")
            .append("       Cartao cr ")
            .append(" WHERE cr.assinatura = a ")
            .append("   AND cr.pessoa.id = :id ")
            .append(" ORDER BY c.id DESC ");

        Query q = em.createQuery(hql.toString());
        q.setParameter("id", id);
        q.setMaxResults(100);

        return q.getResultList();

    }

}
