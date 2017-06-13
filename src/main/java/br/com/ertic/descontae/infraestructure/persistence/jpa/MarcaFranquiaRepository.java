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
        " WHERE endereco.cidade.id = ?1 " +
        "   AND empreendimento.id = unidade.idEmpreendimento " +
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia" +
        " ORDER BY marca.id ")
        List<Object[]> findAllByCidade(Long idCidade);

}
