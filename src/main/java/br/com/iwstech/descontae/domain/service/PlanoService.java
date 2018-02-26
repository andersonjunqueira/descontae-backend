package br.com.iwstech.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Plano;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.PlanoRepository;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class PlanoService extends RestFullService<Plano, Long> {

    @Autowired
    public PlanoService(PlanoRepository repository) {
        super(repository);
    }

}
