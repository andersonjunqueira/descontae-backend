package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;

@Repository
public class CartaoCustomRepositoryImpl implements CartaoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Page<Object[]> findListaSimples(String filtro, Pageable pageable) {

        StringBuilder hqlWhere = new StringBuilder();
        StringBuilder hqlOrder = new StringBuilder();

        if(filtro != null) {
            hqlWhere.append(" CAST(c.codigo as text) = :filtro OR CAST(p.cpf as text) = :filtro OR LOWER(p.nome) LIKE :filtroLike OR LOWER(cl.nome) LIKE :filtroLike ");
        }

        StringBuilder hqlCount = new StringBuilder()
            .append("SELECT COUNT(*) ")
            .append("  FROM Cartao c ")
            .append("       LEFT JOIN c.pessoa p ")
            .append("       LEFT JOIN c.assinatura a ")
            .append("       LEFT JOIN a.cliente cl ");

        StringBuilder hql = new StringBuilder()
            .append("SELECT c.id, c.codigo, p.nome, p.cpf, coalesce(cl.nome, p.nome), c.ativo ")
            .append("  FROM Cartao c ")
            .append("       LEFT JOIN c.pessoa p ")
            .append("       LEFT JOIN c.assinatura a ")
            .append("       LEFT JOIN a.cliente cl ");

        if(hqlWhere.length() > 0) {
            hql.append(" WHERE ").append(hqlWhere.toString());
            hqlCount.append(" WHERE ").append(hqlWhere.toString());
        }

        if(pageable != null && pageable.getSort() != null) {
            hql.append(" ORDER BY ");
            for(Order o : pageable.getSort()) {
                hqlOrder.append(hqlOrder.length() > 0 ? ", " : "");
                hqlOrder.append(o.getProperty()).append(" ").append(o.getDirection().name());
            }
            hql.append(hqlOrder.toString());
        }

        Query q = em.createQuery(hql.toString());
        Query qc = em.createQuery(hqlCount.toString());

        if(filtro != null) {
            q.setParameter("filtro", filtro);
            q.setParameter("filtroLike", (filtro + "%").toLowerCase());
            qc.setParameter("filtro", filtro);
            qc.setParameter("filtroLike", (filtro + "%").toLowerCase());
        }

        if (pageable == null) {
            pageable = new PageRequest(0, Integer.MAX_VALUE);
        }

        Long total = (Long)qc.getSingleResult();
        int start = pageable.getPageSize() * pageable.getPageNumber();
        q.setFirstResult(start);
        q.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(q.getResultList(), pageable, total);

    }

}
