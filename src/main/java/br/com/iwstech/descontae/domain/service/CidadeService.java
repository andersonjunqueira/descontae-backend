package br.com.iwstech.descontae.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iwstech.descontae.domain.model.Cidade;
import br.com.iwstech.descontae.infraestructure.persistence.jpa.CidadeRepository;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.service.RestFullService;

@Service
public class CidadeService extends RestFullService<Cidade, Long> {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    CidadeService(CidadeRepository repository) {
        super(repository);
    }

    public List<Cidade> findAllComParcerias() {
        return ((CidadeRepository)repository).findAllComParcerias();
    }

    public Cidade findByNomeAndSigla(String nome, String siglaEstado) throws NegocioException {
        return findByNomeAndSigla(nome, siglaEstado, false);
    }

    public Cidade findByNomeAndSigla(String nome, String siglaEstado, boolean create) throws NegocioException {

        Cidade c = ((CidadeRepository)repository).findByNomeAndSigla(nome, siglaEstado);

        if(c == null && create) {
            c = new Cidade();
            c.setEstado(estadoService.findBySigla(siglaEstado));
            c.setNome(nome);
            c = addOrUpdate(c);
        }

        return c;
    }
}
