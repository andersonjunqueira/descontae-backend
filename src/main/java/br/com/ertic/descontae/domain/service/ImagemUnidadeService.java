package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.ImagemUnidade;
import br.com.ertic.descontae.infraestructure.persistence.jpa.ImagemUnidadeRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class ImagemUnidadeService extends RestFullService<ImagemUnidade, Long> {

    @Autowired
    public ImagemUnidadeService(ImagemUnidadeRepository repository) {
        super(repository);
    }

}
