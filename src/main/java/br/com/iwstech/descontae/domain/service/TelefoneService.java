package br.com.iwstech.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Telefone;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.TelefoneRepository;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class TelefoneService extends RestFullService<Telefone, Long> {

    @Autowired
    public TelefoneService(TelefoneRepository repository) {
        super(repository);
    }

}
