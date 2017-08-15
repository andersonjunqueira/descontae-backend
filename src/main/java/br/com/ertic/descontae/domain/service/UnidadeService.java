package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Unidade;
import br.com.ertic.descontae.infraestructure.persistence.jpa.UnidadeRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class UnidadeService extends RestFullService<Unidade, Long> {

    @Autowired
    UnidadeService(UnidadeRepository repository) {
        super(repository);
    }
}
