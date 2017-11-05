package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.iwstech.descontae.interfaces.web.dto.ConsumoUsuarioDTO;

@Repository
public class ConsumoCustomRepositoryImpl implements ConsumoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public  List<ConsumoUsuarioDTO> findConsumosUsuario(Long id) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT new br.com.iwstech.descontae.interfaces.web.dto.ConsumoUsuarioDTO(")
            .append("       u.parceiro.marca.nome, u.nome, o.descricao, c.data) ")
            .append("  FROM Consumo c ")
            .append("       INNER JOIN c.assinatura a ")
            .append("       INNER JOIN c.oferta o ")
            .append("       INNER JOIN c.unidade u, ")
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
