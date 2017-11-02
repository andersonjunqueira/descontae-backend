package br.com.iwstech.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.ImagemUnidade;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.ImagemUnidadeRepository;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class ImagemUnidadeService extends RestFullService<ImagemUnidade, Long> {

    @Autowired
    public ImagemUnidadeService(ImagemUnidadeRepository repository) {
        super(repository);
    }

}
