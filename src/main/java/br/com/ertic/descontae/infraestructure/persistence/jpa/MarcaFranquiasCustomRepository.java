package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

public interface MarcaFranquiasCustomRepository {

    List<Object[]> findAllByCidade(Long idCidade, List<Long> categorias);
    List<Object[]> findAllByCidade(Long idCidade, String filtro, List<Long> categorias);

}
