package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Consumo;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ConsumoRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class ConsumoService extends RestFullService<Consumo, Long> {

    @Autowired
    public ConsumoService(ConsumoRepository repository) {
        super(repository);
    }

}
