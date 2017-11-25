package br.com.iwstech.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Configuracao;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.ConfiguracaoRepository;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class ConfiguracaoService extends RestFullService<Configuracao, Long> {

    @Autowired
    ConfiguracaoService(ConfiguracaoRepository repository) {
        super(repository);
    }

}
