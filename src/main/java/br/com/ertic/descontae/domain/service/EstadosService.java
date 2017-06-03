package br.com.ertic.descontae.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Estado;
import br.com.ertic.descontae.infraestructure.persistence.jpa.EstadosRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class EstadosService extends RestFullService<Estado, Long> {

    @Autowired
    EstadosService(EstadosRepository repository) {
        super(repository);
    }

    @Override
    public List<Estado> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "nome"));
    }

}
