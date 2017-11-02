package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Repository;

import br.com.iwstech.descontae.domain.model.Consumo;
import br.com.iwstech.descontae.domain.model.SituacaoAtivo;

@Eager
@Repository
public class OfertaCustomRepositoryImpl implements OfertaCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Page<Object[]> findListaSimples(String texto, SituacaoAtivo situacao, Pageable pageable) {

        StringBuilder hqlWhere = new StringBuilder();

        if(situacao != null) {
            hqlWhere.append(" o.situacao = :situacao ");
        }

        if(texto != null) {
            hqlWhere.append(" o.descricao LIKE :texto ");
        }

        StringBuilder hqlCount = new StringBuilder()
            .append("SELECT COUNT(*) ")
            .append("  FROM Oferta o ");

        StringBuilder hql = new StringBuilder()
            .append("SELECT DISTINCT o.id, o.descricao, m.nome ")
            .append("  FROM OfertaUnidade ou ")
            .append("       JOIN ou.oferta o ")
            .append("       JOIN ou.unidade u ")
            .append("       JOIN u.parceiro p ")
            .append("       JOIN p.marca m ");

        if(hqlWhere.length() > 0) {
            hql.append(" WHERE ").append(hqlWhere.toString());
            hqlCount.append(" WHERE ").append(hqlWhere.toString());
        }

        Query q = em.createQuery(hql.toString());
        Query qc = em.createQuery(hqlCount.toString());

        if(situacao != null) {
            q.setParameter("situacao", situacao);
            qc.setParameter("situacao", situacao);
        }

        if(texto != null) {
            q.setParameter("texto", "%" + texto + "%");
            qc.setParameter("texto", "%" + texto + "%");
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

    @Override
    public Consumo findUltimoConsumo(long idOferta, long idPessoa) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT c ")
            .append("  FROM Consumo c ")
            .append("       JOIN c.oferta o ")
            .append("       JOIN c.assinatura a, ")
            .append("       Cartao cr ")
            .append(" WHERE cr.assinatura = c.assinatura ")
            .append("   AND o.id = :idOferta ")
            .append("   AND cr.pessoa.id = :idPessoa ")
            .append(" ORDER BY c.data DESC ");

        Query q = em.createQuery(hql.toString());
        q.setParameter("idOferta", idOferta);
        q.setParameter("idPessoa", idPessoa);
        q.setMaxResults(1);

        try {
            return (Consumo)q.getSingleResult();
        } catch(NoResultException ex) {
            return null;
        }

    }

}
