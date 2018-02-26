package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.iwstech.descontae.domain.model.Consumo;
import br.com.iwstech.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface ConsumoRepository extends RepositoryBase<Consumo, Long> {

    @Query(value=
        "SELECT c.ativo, count(*) " +
        "  FROM Cartao c " +
        "       JOIN c.assinatura a " +
        "       JOIN a.cliente cl " +
        " WHERE cl.id = ?1 " +
        " GROUP BY c.ativo ")
    List<Object[]> findCartoesAtivos(Long idCliente);

    @Query(value=
        "SELECT ct.nome, count(*) " +
        "  FROM Consumo c " +
        "       JOIN c.unidade u " +
        "       JOIN u.parceiro p " +
        "       JOIN p.categoria ct " +
        "       JOIN c.assinatura a " +
        "       JOIN a.cliente cl " +
        " WHERE cl.id = ?1 " +
        "   AND c.data BETWEEN ?2 AND ?3 " +
        " GROUP BY ct.nome ")
    List<Object[]> findTotaisByCategoria(Long idCliente, Date dataInicio, Date dataFim);

    @Query(value=
        "SELECT count(*) " +
        "  FROM Consumo c  " +
        "       JOIN c.assinatura a " +
        "       JOIN a.cliente cl " +
        " WHERE cl.id = ?1 " +
        "   AND c.data BETWEEN ?2 AND ?3")
    List<Object[]> findTotais(Long idCliente, Date dataInicio, Date dataFim);

    @Query(value=
        "SELECT e.cidade.nome, count(*) " +
        "  FROM Consumo c " +
        "       JOIN c.assinatura a " +
        "       JOIN a.cliente cl " +
        "       JOIN a.pessoa p " +
        "       JOIN p.endereco e " +
        " WHERE cl.id = ?1 " +
        "   AND c.data BETWEEN ?2 AND ?3" +
        " GROUP BY e.cidade.nome ")
    List<Object[]> findTotaisByCidade(Long idCliente, Date dataInicio, Date dataFim);

    @Query(value=
        "SELECT e.bairro, count(*) " +
        "  FROM Consumo c  " +
        "       JOIN c.assinatura a " +
        "       JOIN a.cliente cl " +
        "       JOIN a.pessoa p " +
        "       JOIN p.endereco e " +
        " WHERE cl.id = ?1 " +
        "   AND e.cidade.id = ?2 " +
        "   AND c.data BETWEEN ?3 AND ?4 " +
        " GROUP BY e.bairro ")
    List<Object[]> findTotaisByBairro(Long idCidade, Date dataInicio, Date dataFim);
}
