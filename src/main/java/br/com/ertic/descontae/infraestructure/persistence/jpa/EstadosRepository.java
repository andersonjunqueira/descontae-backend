package br.com.ertic.descontae.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ertic.descontae.domain.model.Estado;

public interface EstadosRepository extends JpaRepository<Estado, Long> {

}
