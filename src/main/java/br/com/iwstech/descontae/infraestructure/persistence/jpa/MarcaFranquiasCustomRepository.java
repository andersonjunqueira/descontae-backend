package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.iwstech.descontae.domain.model.MarcaFranquia;

public interface MarcaFranquiasCustomRepository {

    Page<MarcaFranquia> findAllMarcas(Example<MarcaFranquia> example, Pageable pageable);
    List<Long> findAllMarcasComOfertasAtivas(Long idCidade, String filtro, List<Long> categorias);
    List<Object[]> findUnidadesByCidadeEMarca(Long idCidade, Long idMarca);

}
