package br.com.ertic.descontae.infraestructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CartaoCustomRepositoryImpl implements CartaoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Page<Object[]> findListaSimples(String filtro, Pageable pageable) {

        StringBuilder hqlWhere = new StringBuilder();

        if(filtro != null) {
            hqlWhere.append(" c.codigo = :filtro OR p.nome LIKE :filtroLike");
        }

        StringBuilder hqlCount = new StringBuilder()
            .append("SELECT COUNT(*) ")
            .append("  FROM Cartao c ")
            .append("       LEFT JOIN c.pessoa p ");

        StringBuilder hql = new StringBuilder()
            .append("SELECT c.id, c.codigo, p.nome ")
            .append("  FROM Cartao c ")
            .append("       LEFT JOIN c.pessoa p ");

        if(hqlWhere.length() > 0) {
            hql.append(" WHERE ").append(hqlWhere.toString());
            hqlCount.append(" WHERE ").append(hqlWhere.toString());
        }

        Query q = em.createQuery(hql.toString());
        Query qc = em.createQuery(hqlCount.toString());

        if(filtro != null) {
            q.setParameter("filtro", filtro);
            q.setParameter("filtroLike", filtro + "%");
            qc.setParameter("filtro", filtro);
            qc.setParameter("filtroLike", filtro + "%");
        }

        if (pageable == null) {
            pageable = new PageRequest(0, Integer.MAX_VALUE);
        }

        Long total = (Long)qc.getSingleResult();
        int paginas = total.intValue() / pageable.getPageSize();
        paginas += (total.intValue() % pageable.getPageSize() > 0 ? 1 : 0);

        int start = pageable.getPageSize() * pageable.getPageNumber();

        q.setFirstResult(start);
        q.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(q.getResultList(), pageable, total);

    }

}
