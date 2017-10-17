package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class FranquiasCustomRepositoryImpl implements FranquiasCustomRepository {

    private String HOME_QUERY =
        "SELECT unidade.id, " +
        "       marca.id, " +
        "       marca.nome, " +
        "       marca.logomarca, " +
        "       marca.imagemFundoApp, " +
        "       categoria.nome, " +
        "       oferta.descricao, " +
        "       oferta.regras, " +
        "       oferta.valor, " +
        "       oferta.desconto, " +
        "       endereco.latitude, " +
        "       endereco.longitude, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro || ', ' || endereco.cidade.nome || ' - ' || endereco.cidade.estado.sigla, " +
        "       endereco.cep, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro, " +
        "       unidade.inicioExpediente, " +
        "       unidade.fimExpediente " +
        "       FROM OfertaUnidade ofertaunidade " +
        "       JOIN ofertaunidade.oferta oferta " +
        "       JOIN ofertaunidade.unidade unidade " +
        "       JOIN unidade.endereco endereco, " +
        "       Parceiro parceiro " +
        "       JOIN parceiro.marca marca " +
        "       JOIN parceiro.categoria categoria " +
        " WHERE endereco.cidade.id = :idCidade " +
        "   AND parceiro = unidade.parceiro " +
        "   AND oferta.situacao = 'A' ";

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> findAllByCidade(Long idCidade, List<Long> categorias) {

        StringBuilder hql = new StringBuilder(HOME_QUERY);
        if(categorias != null) {
            hql.append(" AND categoria.id in :catz ");
        }

        Query q = em.createQuery(hql.toString());
        q.setParameter("idCidade",  idCidade);

        if(categorias != null) {
            q.setParameter("catz",  categorias);
        }

        return q.getResultList();

    }

    @Override
    public List<Object[]> findAllByCidade(Long idCidade, String filtro, List<Long> categorias) {

        StringBuilder hql = new StringBuilder(HOME_QUERY);

        if(filtro != null) {
            hql.append(" AND (LOWER(categoria.nome) LIKE :filtro OR LOWER(unidade.nome) LIKE :filtro OR LOWER(parceiro.nomeFantasia) LIKE :filtro OR LOWER(parceiro.nome) LIKE :filtro) ");
        }

        if(categorias != null) {
            hql.append(" AND categoria.id in :catz ");
        }

        Query q = em.createQuery(hql.toString());
        q.setParameter("idCidade",  idCidade);

        if(filtro != null) {
            q.setParameter("filtro", filtro);
        }

        if(categorias != null) {
            q.setParameter("catz",  categorias);
        }

        return q.getResultList();

    }

}
