package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.ertic.descontae.domain.model.MarcaFranquia;

@Repository
public class MarcaFranquiasCustomRepositoryImpl implements MarcaFranquiasCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> findAllMarcasComOfertasAtivas(Long idCidade, String filtro, List<Long> categorias) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT DISTINCT marca.id ")
            .append("  FROM OfertaUnidade ofertaunidade ")
            .append("       JOIN ofertaunidade.oferta oferta ")
            .append("       JOIN ofertaunidade.unidade unidade ")
            .append("       JOIN unidade.endereco endereco, ")
            .append("       Parceiro parceiro ")
            .append("       JOIN parceiro.marca marca ")
            .append("       JOIN parceiro.categoria categoria ")
            .append(" WHERE parceiro = unidade.parceiro ")
            .append("   AND parceiro.excluido = 'N' ")
            .append("   AND oferta.situacao = 'A' ")
            .append("   AND endereco.cidade.id = :idCidade ");

        if(categorias != null) {
            hql.append(" AND categoria.id in :catz ");
        }

        if(filtro != null) {
            hql.append(" AND (LOWER(categoria.nome) LIKE :filtro OR LOWER(unidade.nome) LIKE :filtro OR LOWER(parceiro.nomeFantasia) LIKE :filtro OR LOWER(parceiro.nome) LIKE :filtro) ");
        }

        Query q = em.createQuery(hql.toString());
        q.setParameter("idCidade",  idCidade);

        if(categorias != null) {
            q.setParameter("catz",  categorias);
        }

        if(filtro != null) {
            q.setParameter("filtro", filtro);
        }

        return (List<Long>)q.getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> findUnidadesByCidadeEMarca(Long idCidade, Long idMarca) {

        StringBuilder hql = new StringBuilder()
            .append("SELECT DISTINCT unidade.id, ")
            .append("       marca.id, ")
            .append("       marca.nome, ")
            .append("       marca.logomarca, ")
            .append("       marca.imagemFundoApp, ")
            .append("       categoria.nome, ")
            .append("       oferta.descricao, ")
            .append("       oferta.regras, ")
            .append("       oferta.valor, ")
            .append("       oferta.desconto, ")
            .append("       endereco.latitude, ")
            .append("       endereco.longitude, ")
            .append("       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro || ', ' || endereco.cidade.nome || ' - ' || endereco.cidade.estado.sigla, ")
            .append("       endereco.cep, ")
            .append("       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro, ")
            .append("       unidade.inicioExpediente, ")
            .append("       unidade.fimExpediente, ")
            .append("       oferta.desconto ")
            .append("  FROM OfertaUnidade ofertaunidade ")
            .append("       JOIN ofertaunidade.oferta oferta ")
            .append("       JOIN ofertaunidade.unidade unidade ")
            .append("       JOIN unidade.endereco endereco, ")
            .append("       Parceiro parceiro ")
            .append("       JOIN parceiro.marca marca ")
            .append("       JOIN parceiro.categoria categoria ")
            .append(" WHERE parceiro = unidade.parceiro ")
            .append("   AND parceiro.excluido = 'N' ")
            .append("   AND oferta.situacao = 'A' ")
            .append("   AND endereco.cidade.id = :idCidade ")
            .append("   AND marca.id = :idMarca ");

        Query q = em.createQuery(hql.toString());
        q.setParameter("idCidade",  idCidade);
        q.setParameter("idMarca",  idMarca);

        return q.getResultList();

    }

}
