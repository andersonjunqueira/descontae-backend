package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.iwstech.descontae.interfaces.web.dto.ConsumoUsuarioDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardChaveValorDTO;
import br.com.iwstech.descontae.interfaces.web.dto.DashboardSituacaoValorDTO;

@Repository
public class ConsumoCustomRepositoryImpl implements ConsumoCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public  List<ConsumoUsuarioDTO> findByUsuario(Long id) {

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

    @Override
    public List<DashboardSituacaoValorDTO> findCartoesAtivos() {
        return findCartoesAtivos(null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DashboardSituacaoValorDTO> findCartoesAtivos(Long idCliente) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT new br.com.iwstech.descontae.interfaces.web.dto.DashboardSituacaoValorDTO(c.ativo, count(*)) ")
            .append("  FROM Cartao c ")
            .append("       JOIN c.assinatura a ")
            .append("       JOIN a.cliente cl ");

        if(idCliente != null) {
            hql.append(" WHERE cl.id = :idCliente ");
        }

        hql.append(" GROUP BY c.ativo ");

        Query q = em.createQuery(hql.toString());

        if(idCliente != null) {
            q.setParameter("idCliente", idCliente);

        }

        return q.getResultList();
    }

    @Override
    public List<DashboardChaveValorDTO> findTotaisByCategoria(Long idCidade, Date dataInicio, Date dataFim) {
        return findTotaisByCategoria(null, idCidade, dataInicio, dataFim);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DashboardChaveValorDTO> findTotaisByCategoria(Long idCliente, Long idCidade, Date dataInicio, Date dataFim) {

        StringBuilder hql = new StringBuilder()
           .append("SELECT new br.com.iwstech.descontae.interfaces.web.dto.DashboardChaveValorDTO(ct.nome, count(*)) ")
           .append("  FROM Consumo c ")
           .append("       JOIN c.unidade u ")
           .append("       JOIN u.endereco e ")
           .append("       JOIN u.parceiro p ")
           .append("       JOIN p.categoria ct ")
           .append("       JOIN c.assinatura a ")
           .append("       JOIN a.cliente cl ")
           .append(" WHERE c.data BETWEEN :di AND :df ")
           .append("   AND e.cidade.id = :idCidade ");

        if(idCliente != null) {
            hql.append(" AND cl.id = :idCliente ");
        }

        hql.append(" GROUP BY ct.nome ");

        Query q = em.createQuery(hql.toString());

        if(idCliente != null) {
            q.setParameter("idCliente", idCliente);
        }

        q.setParameter("idCidade", idCidade);
        q.setParameter("di", dataInicio);
        q.setParameter("df", dataFim);

        return q.getResultList();
    }

    @Override
    public Long findTotais(Date dataInicio, Date dataFim) {
        return findTotais(null, dataInicio, dataFim);
    }

    @Override
    public Long findTotais(Long idCliente, Date dataInicio, Date dataFim) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT count(*) ")
            .append("  FROM Consumo c ")
            .append("       JOIN c.assinatura a ")
            .append("       JOIN a.cliente cl ")
            .append(" WHERE c.data BETWEEN :di AND :df ");

        if(idCliente != null) {
            hql.append(" AND cl.id = :idCliente ");
        }

        Query q = em.createQuery(hql.toString());

        if(idCliente != null) {
            q.setParameter("idCliente", idCliente);
        }

        q.setParameter("di", dataInicio);
        q.setParameter("df", dataFim);

        return (Long)q.getSingleResult();
    }

    @Override
    public List<DashboardChaveValorDTO> findTotaisByCidade(Date dataInicio, Date dataFim) {
        return findTotaisByCidade(null, dataInicio, dataFim);
    }

    @Override
    public List<DashboardChaveValorDTO> findTotaisByCidade(Long idCliente, Date dataInicio, Date dataFim) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT new br.com.iwstech.descontae.interfaces.web.dto.DashboardChaveValorDTO(e.cidade.nome, count(*)) ")
            .append("  FROM Consumo c  ")
            .append("       JOIN c.assinatura a ")
            .append("       JOIN a.cliente cl ")
            .append("       JOIN a.pessoa p ")
            .append("       JOIN p.endereco e ")
            .append(" WHERE c.data BETWEEN :di AND :df ");

        if(idCliente != null) {
            hql.append(" AND cl.id = :idCliente ");
        }

        hql.append(" GROUP BY e.cidade.nome ");

        Query q = em.createQuery(hql.toString());

        if(idCliente != null) {
            q.setParameter("idCliente", idCliente);
        }

        q.setParameter("di", dataInicio);
        q.setParameter("df", dataFim);

        return q.getResultList();
    }

    @Override
    public List<DashboardChaveValorDTO> findTotaisByBairro(Long idCidade, Date dataInicio, Date dataFim) {
        return findTotaisByBairro(null, idCidade, dataInicio, dataFim);
    }

    @Override
    public List<DashboardChaveValorDTO> findTotaisByBairro(Long idCliente, Long idCidade, Date dataInicio, Date dataFim) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT new br.com.iwstech.descontae.interfaces.web.dto.DashboardChaveValorDTO(e.bairro, count(*)) ")
            .append("  FROM Consumo c  ")
            .append("       JOIN c.unidade u ")
            .append("       JOIN u.endereco e ")
            .append("       JOIN c.assinatura a ")
            .append("       JOIN a.cliente cl ")
            .append("       JOIN a.pessoa p ")
            .append(" WHERE e.cidade.id = :idCidade ")
            .append("   AND c.data BETWEEN :di AND :df ");

        if(idCliente != null) {
            hql.append(" AND cl.id = :idCliente ");
        }

        hql.append(" GROUP BY e.bairro");

        Query q = em.createQuery(hql.toString());

        if(idCliente != null) {
            q.setParameter("idCliente", idCliente);
        }

        q.setParameter("idCidade", idCidade);
        q.setParameter("di", dataInicio);
        q.setParameter("df", dataFim);

        return q.getResultList();
    }
}
