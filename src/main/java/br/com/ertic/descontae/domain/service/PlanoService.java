package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Plano;
import br.com.ertic.descontae.infraestructure.persistence.jpa.PlanoRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class PlanoService extends RestFullService<Plano, Long> {

    @Autowired
    public PlanoService(PlanoRepository repository) {
        super(repository);
    }

}
