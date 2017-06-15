package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.MarcaFranquia;

@Eager
public interface MarcaFranquiaRepository extends JpaRepository<MarcaFranquia, Long> {

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
        "       endereco.latitude, " +
        "       endereco.longitude, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro || ', ' || endereco.cidade.nome || ' - ' || endereco.cidade.estado.sigla, " +
        "       endereco.cep, " +
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro " +
        "       FROM OfertaUnidade ofertaunidade " +
        "       JOIN ofertaunidade.revista revista " +
        "       JOIN ofertaunidade.oferta oferta " +
        "       JOIN ofertaunidade.unidade unidade " +
        "       JOIN unidade.endereco endereco, " +
        "       Empreendimento empreendimento " +
        "       JOIN empreendimento.marca marca " +
        "       JOIN empreendimento.categoria categoria " +
        " WHERE endereco.cidade.id = ?1 " +
        "   AND empreendimento.id = unidade.idEmpreendimento " +
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia" +
        " ORDER BY marca.id ")
    List<Object[]> findAllByCidade(Long idCidade);

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
        "       endereco.cidade.id " +
        "       FROM OfertaUnidade ofertaunidade " +
        "       JOIN ofertaunidade.revista revista " +
        "       JOIN ofertaunidade.oferta oferta " +
        "       JOIN ofertaunidade.unidade unidade " +
        "       JOIN unidade.endereco endereco, " +
        "       Empreendimento empreendimento " +
        "       JOIN empreendimento.marca marca " +
        "       JOIN empreendimento.categoria categoria " +
        " WHERE unidade.id = ?1 " +
        "   AND empreendimento.id = unidade.idEmpreendimento " +
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
        "       Empreendimento empreendimento " +
        "       JOIN empreendimento.marca marca " +
        "       JOIN empreendimento.categoria categoria " +
        " WHERE marca.id = ?1 " +
        "   AND endereco.cidade.id = ?2 " +
        "   AND empreendimento.id = unidade.idEmpreendimento " +
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia ")
    List<Object[]> findOutrasUnidades(Long idMarca, Long idCidade);

}
