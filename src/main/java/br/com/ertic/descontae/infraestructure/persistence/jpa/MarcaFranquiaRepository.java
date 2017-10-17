package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.cdi.Eager;

import br.com.ertic.descontae.domain.model.MarcaFranquia;
import br.com.ertic.util.infraestructure.jpa.RepositoryBase;

@Eager
public interface MarcaFranquiaRepository extends RepositoryBase<MarcaFranquia, Long> {

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
