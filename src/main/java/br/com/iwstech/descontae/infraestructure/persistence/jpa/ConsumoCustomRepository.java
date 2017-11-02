package br.com.iwstech.descontae.infraestructure.persistence.jpa;

import java.util.List;

import br.com.iwstech.descontae.interfaces.web.dto.ConsumoUsuarioDTO;

public interface ConsumoCustomRepository {

    List<ConsumoUsuarioDTO> findConsumosUsuario(Long id);

}
