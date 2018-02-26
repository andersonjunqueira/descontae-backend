package br.com.iwstech.descontae.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Estado;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.EstadoRepository;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class EstadoService extends RestFullService<Estado, Long> {

    @Autowired
    EstadoService(EstadoRepository repository) {
        super(repository);
    }

    @Override
    public List<Estado> findAll(Map<String, String[]> params) {
        return repository.findAll(new Sort(Sort.Direction.ASC, "nome"));
    }

    public Estado findBySigla(String sigla) {
        return ((EstadoRepository)repository).findBySigla(sigla);
    }
}
