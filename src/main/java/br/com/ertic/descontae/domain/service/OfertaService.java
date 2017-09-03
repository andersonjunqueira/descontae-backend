package br.com.ertic.descontae.domain.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.ertic.descontae.domain.model.Oferta;
import br.com.ertic.descontae.domain.model.SituacaoOferta;
import br.com.ertic.descontae.infraestructure.persistence.jpa.OfertaCustomRepository;
import br.com.ertic.descontae.infraestructure.persistence.jpa.OfertaRepository;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.service.RestFullService;

@Service
public class OfertaService extends RestFullService<Oferta, Long> {

    @Autowired
    private OfertaCustomRepository customRepository;

    @Autowired
    public OfertaService(OfertaRepository repository) {
        super(repository);
    }

    public Page<Object[]> findListaSimples(Map<String, String[]> params) throws NegocioException {

        SituacaoOferta situacao = null;
        if(params.get("situacao") != null) {
            if(params.get("situacao")[0].equals("A")) {
                situacao = SituacaoOferta.A;
            } else if(params.get("situacao")[0].equals("I")) {
                situacao = SituacaoOferta.I;
            }
        }

        return customRepository.findListaSimples(
            params.get("texto") != null ? params.get("texto")[0] : null,
            situacao, getPageRequest(params));
    }

}
