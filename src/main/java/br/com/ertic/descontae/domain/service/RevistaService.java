package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Revista;
import br.com.ertic.descontae.infraestructure.persistence.jpa.RevistaRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class RevistaService extends RestFullService<Revista, Long> {

    @Autowired
    public RevistaService(RevistaRepository repository) {
        super(repository);
    }

}
