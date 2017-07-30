package br.com.ertic.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Parceiro;
import br.com.ertic.descontae.domain.service.ParceiroService;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/parceiros")
public class ParceirosController extends RestFullEndpoint<Parceiro, Long> {

    @Autowired
    public ParceirosController(ParceiroService service) {
        super(service);
    }

}

