package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.Unidade;

@Eager
public interface UnidadeRepository extends JpaRepository<Unidade, Long> {

    @Query(value=
        "SELECT unidade.id, " +
        "       marca.nome, marca.logomarca, " +
        "       categoria.nome,  " +
        "       endereco.latitude, endereco.longitude, " +
        "       unidade.inicioExpediente, unidade.fimExpediente, " +
        "       (select sum(a.satisfacao) / count(*) from Avaliacao a WHERE a.idUnidade = unidade.id) " +
        "  FROM Empreendimento empreendimento  " +
        "       JOIN empreendimento.unidades unidade " +
        "       JOIN empreendimento.marca marca  " +
        "       JOIN empreendimento.categoria categoria " +
        "       JOIN unidade.endereco endereco " +
        "       JOIN endereco.cidade cidade  " +
        "       JOIN cidade.estado estado,  " +
        "       OfertaUnidade ofertaunidade  " +
        "       JOIN ofertaunidade.revista revista " +
        " WHERE cidade.id = ?1 " +
        "   AND ofertaunidade.unidade = unidade " +
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia")
    List<Object[]> findUnidadesByIdCidade(Long idCidade);

    @Query(value=
        "SELECT unidade.id, " +
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
        "       endereco.logradouro || ' ' || endereco.complemento || ' ' || endereco.numero || ', ' || endereco.bairro " +
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
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia")
        List<Object[]> findDetalhesUnidade(Long idUnidade);

}
