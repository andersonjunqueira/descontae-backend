package br.com.ertic.descontae.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Unidade;
import br.com.ertic.descontae.infraestructure.persistence.jpa.UnidadeRepository;
import br.com.ertic.descontae.interfaces.web.dto.OfertaUnidadeDTO;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class UnidadeService extends RestFullService<Unidade, Long> {

    @Autowired
    UnidadeService(UnidadeRepository repository) {
        super(repository);
    }

    public List<OfertaUnidadeDTO> findAllByMarcaSelecionadoPorOferta(Long idMarca, Long idOferta) throws NegocioException {
        return ((UnidadeRepository)repository).findAllByMarcaSelecionadoPorOferta(idMarca, idOferta);
    }

}
