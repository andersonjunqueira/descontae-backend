package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ertic.descontae.domain.model.Empreendimento;
import br.com.ertic.descontae.interfaces.web.dto.ListaEmpreendimentoDTO;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {

    @Query(value=
        "SELECT new br.com.ertic.descontae.interfaces.web.dto.ListaEmpreendimentoDTO(unidade, marca, categoria) " +
        "  FROM Empreendimento emp " +
        "       JOIN emp.marca marca " +
        "       JOIN emp.categoria cat " +
        "       JOIN emp.unidades un " +
        "       JOIN u.endereco ender " +
        "       JOIN ender.cidade cid " +
        "       JOIN c.estado uf,  " +
        "       OfertaUnidade ou " +
        " WHERE uf.sigla = ?1 " +
        "   AND ou.unidade = un ")
    List<ListaEmpreendimentoDTO> findUnidadesByUF(String uf);

}
