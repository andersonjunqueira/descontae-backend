package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Cliente;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ClienteRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class ClienteService extends RestFullService<Cliente, Long> {

    @Autowired
    public ClienteService(ClienteRepository repository) {
        super(repository);
    }

}
