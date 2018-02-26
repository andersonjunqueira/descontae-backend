package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartaoCustomRepository {

    Page<Object[]> findListaSimples(String filtro, Pageable pageable);

}
