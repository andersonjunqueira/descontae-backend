package br.com.ertic.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Estado;
import br.com.ertic.descontae.domain.service.EstadosService;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@RequestMapping("/estados")
public class EstadosRest extends RestFullEndpoint<Estado, Long> {

    @Autowired
    public EstadosRest(EstadosService service) {
        super(service);
    }

}

