package br.com.ertic.descontae.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Categoria;
import br.com.ertic.descontae.infraestructure.persistence.jpa.CategoriaRepository;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class CategoriaService extends RestFullService<Categoria, Long> {

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }

    @Override
    public void delete(Long id) throws NegocioException {
        long totalEmUso = ((CategoriaRepository)repository).findTotalEmUso(id);
        if(totalEmUso == 0) {
            super.delete(id);
        } else {
            throw new NegocioException("registro-em-uso");
        }
    }
}
