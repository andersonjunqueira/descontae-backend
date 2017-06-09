package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ertic.descontae.domain.model.Empreendimento;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {

    @Query(value=
        "SELECT unidade.id, " +
        "       marca.nome, marca.caminhoLogomarca, " +
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

}
