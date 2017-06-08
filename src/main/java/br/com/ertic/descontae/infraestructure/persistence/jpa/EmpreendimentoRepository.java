package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ertic.descontae.domain.model.Empreendimento;
import br.com.ertic.descontae.interfaces.web.dto.ListaEmpreendimentoDTO;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {

    @Query(value=
        "SELECT DISTINCT new br.com.ertic.descontae.interfaces.web.dto.ListaEmpreendimentoDTO(unidade, marca, categoria) " +
        "  FROM Empreendimento empreendimento " +
        "  JOIN empreendimento.marca marca " +
        "  JOIN empreendimento.categoria categoria " +
        "  JOIN empreendimento.unidades unidade " +
        "  JOIN unidade.endereco endereco " +
        "  JOIN endereco.cidade cidade " +
        "  JOIN cidade.estado estado, " +
        "  OfertaUnidade ofertaunidade " +
        "  JOIN ofertaunidade.revista revista " +
        " WHERE cidade.id = ?1 " +
        "   AND ofertaunidade.unidade = unidade " +
        "   AND current_date() BETWEEN revista.inicioVigencia AND revista.fimVigencia ")
    List<ListaEmpreendimentoDTO> findUnidadesByIdCidade(Long idCidade);

}
