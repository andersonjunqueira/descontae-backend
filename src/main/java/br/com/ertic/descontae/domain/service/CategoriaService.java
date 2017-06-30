package br.com.ertic.descontae.domain.service;

import static org.springframework.data.domain.ExampleMatcher.matching;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Categoria;
import br.com.ertic.descontae.infraestructure.persistence.jpa.CategoriaRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class CategoriaService extends RestFullService<Categoria, Long> {

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        super(repository);
    }

    @Override
    public List<Categoria> findAll(Map<String, String[]> params) {

        if(params.get("nome") != null) {

            Categoria c = new Categoria();
            c.setNome(params.get("nome")[0]);
            Example<Categoria> example = Example.of(c, matching()
                .withMatcher("nome", matcher -> matcher.startsWith().ignoreCase()));

            return super.findAll(example);

        }

        return super.findAll();
    }
}
