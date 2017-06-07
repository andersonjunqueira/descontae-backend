package br.com.ertic.descontae.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Cidade;
import br.com.ertic.descontae.infraestructure.persistence.jpa.CidadeRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class CidadeService extends RestFullService<Cidade, Long> {

    @Autowired
    CidadeService(CidadeRepository repository) {
        super(repository);
    }

    public List<Cidade> findAllComParcerias() {
        return ((CidadeRepository)repository).findAllComParcerias();
    }

}
