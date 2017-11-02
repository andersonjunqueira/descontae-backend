package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import br.com.iwstech.descontae.domain.model.MarcaFranquia;

public interface MarcaFranquiasCustomRepository {

    List<Long> findAllMarcasComOfertasAtivas(Long idCidade, String filtro, List<Long> categorias);
    List<Object[]> findUnidadesByCidadeEMarca(Long idCidade, Long idMarca);

}
