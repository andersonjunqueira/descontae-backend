package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import br.com.iwstech.descontae.domain.model.MarcaFranquia;

@Repository
public class MarcaFranquiasCustomRepositoryImpl implements MarcaFranquiasCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<MarcaFranquia> findAllMarcas(Example<MarcaFranquia> example, Pageable pageable) {

        StringBuilder hqlCount = new StringBuilder()
            .append("SELECT COUNT(*) ")
            .append("  FROM MarcaFranquia marca ");

        StringBuilder hql = new StringBuilder()
            .append("SELECT new br.com.iwstech.descontae.domain.model.MarcaFranquia(marca.id, marca.nome) ")
            .append("  FROM MarcaFranquia marca ");

        StringBuilder hqlOrder = new StringBuilder();

        if(example.getProbe().getNome() != null) {
            hql.append(" WHERE LOWER(marca.nome) LIKE :nome ");
            hqlCount.append(" WHERE LOWER(marca.nome) LIKE :nome ");

        }

        if(pageable.getSort() != null) {
            pageable.getSort().forEach(order -> {
                hqlOrder.append(hqlOrder.length() > 0 ? " , " : "");
                hqlOrder.append(" marca.").append(order.getProperty()).append(" ").append(order.getDirection());
            });
        }

        if(hqlOrder.length() > 0) {
            hql.append(" ORDER BY ").append(hqlOrder.toString());
        }

        Query q = em.createQuery(hql.toString());
        Query qc = em.createQuery(hqlCount.toString());

        if(example.getProbe().getNome() != null) {
            q.setParameter("nome",  example.getProbe().getNome().toLowerCase() + "%");
            qc.setParameter("nome",  example.getProbe().getNome().toLowerCase() + "%");
        }

        Long total = (Long)qc.getSingleResult();
        int paginas = total.intValue() / pageable.getPageSize();
        paginas += (total.intValue() % pageable.getPageSize() > 0 ? 1 : 0);

        int start = pageable.getPageSize() * pageable.getPageNumber();

        q.setFirstResult(start);
        q.setMaxResults(pageable.getPageSize());

        return new PageImpl<MarcaFranquia>(q.getResultList(), pageable, total);

    }

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

        return q.getResultList();

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
