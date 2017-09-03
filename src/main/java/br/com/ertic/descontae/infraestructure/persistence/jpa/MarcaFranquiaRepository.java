package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.MarcaFranquia;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface MarcaFranquiaRepository extends RepositoryBase<MarcaFranquia, Long> {

    String HOME_QUERY =
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
        " WHERE endereco.cidade.id = ?1 " +
        "   AND parceiro = unidade.parceiro " +
        "   AND oferta.situacao = 'A' ";

    @Query(value=HOME_QUERY)
    List<Object[]> findAllByCidade(Long idCidade);

    @Query(value=HOME_QUERY + " AND (categoria.nome LIKE ?2 OR unidade.nome LIKE ?2) ")
    List<Object[]> findAllByCidade(Long idCidade, String filtro);

    @Query(value=
        "SELECT unidade.id, " +
        "       marca.id, " +
        "       marca.nome, " +
        "       marca.logomarca, " +
        "       marca.imagemFundoApp, " +
        "       categoria.nome,  " +
        "       endereco.latitude, " +
        "       endereco.longitude, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro || ', ' || endereco.cidade.nome || ' - ' || endereco.cidade.estado.sigla, " +
        "       endereco.cep, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro, " +
        "       unidade.sobre,  " +
        "       (SELECT COUNT(*) FROM Avaliacao a WHERE a.curtiu = 1 AND a.unidade = unidade), " +
        "       (SELECT COUNT(*) FROM Avaliacao a WHERE a.curtiu = -1 AND a.unidade = unidade), " +
        "       endereco.cidade.id,  " +
        "       unidade.inicioExpediente, " +
        "       unidade.fimExpediente " +
        "  FROM Unidade unidade " +
        "       JOIN unidade.endereco endereco, " +
        "       Parceiro parceiro " +
        "       JOIN parceiro.marca marca " +
        "       JOIN parceiro.categoria categoria " +
        " WHERE unidade.id = ?1 " +
        "   AND parceiro = unidade.parceiro " +
        "   AND unidade.id in (SELECT ou.unidade.id " +
        "                        FROM OfertaUnidade ou " +
        "                       WHERE ou.oferta.situacao = 'A') ")
    List<Object[]> findDetalhes(Long idUnidade);

    @Query(value=
        "SELECT unidade.id, " +
        "    endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro " +
        "FROM Unidade unidade " +
        "    JOIN unidade.endereco endereco, " +
        "    Parceiro parceiro  " +
        "    JOIN parceiro.marca marca " +
        "    JOIN parceiro.categoria categoria " +
        "WHERE marca.id = ?1 " +
        "  AND endereco.cidade.id = ?2 " +
        "  AND parceiro = unidade.parceiro  " +
        "  AND unidade.id in (SELECT ou.unidade.id " +
        "                       FROM OfertaUnidade ou " +
        "                      WHERE ou.oferta.situacao = 'A') ")
    List<Object[]> findOutrasUnidades(Long idMarca, Long idCidade);

}
