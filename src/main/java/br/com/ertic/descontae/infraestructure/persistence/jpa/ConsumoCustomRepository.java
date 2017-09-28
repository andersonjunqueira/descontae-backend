package br.com.ertic.descontae.infraestructure.persistence.jpa;

import java.util.List;

import br.com.ertic.descontae.interfaces.web.dto.ConsumoUsuarioDTO;

public interface ConsumoCustomRepository {

    List<ConsumoUsuarioDTO> findConsumosUsuario(Long id);

}
