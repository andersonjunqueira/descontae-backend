package br.com.ertic.descontae.domain.service;

import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Revista;
import br.com.ertic.descontae.infraestructure.persistence.jpa.RevistaRepository;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class RevistaService extends RestFullService<Revista, Long> {

    @Autowired
    public RevistaService(RevistaRepository repository) {
        super(repository);
    }

    @Override
    public Revista save(Revista e) {

        long t = e.getInicioVigencia().getTime() - TimeZone.getDefault().getOffset(e.getInicioVigencia().getTime());
        e.setInicioVigencia(new Date(t));

        t = e.getFimVigencia().getTime() - TimeZone.getDefault().getOffset(e.getFimVigencia().getTime());
        e.setFimVigencia(new Date(t));

        return super.save(e);
    }
}
