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
        "       JOIN ofertaunidade.revista revista " +
        "       JOIN ofertaunidade.oferta oferta " +
        "       JOIN ofertaunidade.unidade unidade " +
        "       JOIN unidade.endereco endereco, " +
        "       Parceiro parceiro " +
        "       JOIN parceiro.marca marca " +
        "       JOIN parceiro.categoria categoria " +
        " WHERE endereco.cidade.id = ?1 " +
        "   AND parceiro.id = unidade.idParceiro ";

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
        "       categoria.nome, " +
        "       oferta.descricao, " +
        "       oferta.regras, " +
        "       oferta.valor, " +
        "       oferta.desconto, " +
        "       endereco.latitude, endereco.longitude, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro || ', ' || endereco.cidade.nome || ' - ' || endereco.cidade.estado.sigla, endereco.cep, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro, " +
        "       unidade.sobre, " +
        "       (select sum(a.satisfacao) / count(*) from Avaliacao a WHERE a.idUnidade = unidade.id), " +
        "       (select sum(a.preco) / count(*) from Avaliacao a WHERE a.idUnidade = unidade.id), " +

//        "       (SELECT COUNT(*) FROM Avaliacao a WHERE a.curtiu = 1 AND a.idUnidade = unidade.id), " +
//        "       (SELECT COUNT(*) FROM Avaliacao a WHERE a.curtiu = -1 AND a.idUnidade = unidade.id), " +

        "       endereco.cidade.id, " +
        "       unidade.inicioExpediente, " +
        "       unidade.fimExpediente " +
        "       FROM OfertaUnidade ofertaunidade " +
        "       JOIN ofertaunidade.revista revista " +
        "       JOIN ofertaunidade.oferta oferta " +
        "       JOIN ofertaunidade.unidade unidade " +
        "       JOIN unidade.endereco endereco, " +
        "       Parceiro parceiro " +
        "       JOIN parceiro.marca marca " +
        "       JOIN parceiro.categoria categoria " +
        " WHERE unidade.id = ?1 " +
        "   AND parceiro.id = unidade.idParceiro " +
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia ")
    List<Object[]> findDetalhes(Long idUnidade);

    @Query(value=
        "SELECT unidade.id, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro " +
        "       FROM OfertaUnidade ofertaunidade " +
        "       JOIN ofertaunidade.revista revista " +
        "       JOIN ofertaunidade.oferta oferta " +
        "       JOIN ofertaunidade.unidade unidade " +
        "       JOIN unidade.endereco endereco, " +
        "       Parceiro parceiro " +
        "       JOIN parceiro.marca marca " +
        "       JOIN parceiro.categoria categoria " +
        " WHERE marca.id = ?1 " +
        "   AND endereco.cidade.id = ?2 " +
        "   AND parceiro.id = unidade.idParceiro " +
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia ")
    List<Object[]> findOutrasUnidades(Long idMarca, Long idCidade);

}
