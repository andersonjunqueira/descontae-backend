package br.com.iwstech.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.domain.model.Plano;
import br.com.iwstech.descontae.domain.service.PlanoService;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/planos")
public class PlanosController extends RestFullEndpoint<Plano, Long> {

    @Autowired
    public PlanosController(PlanoService service) {
        super(service);
    }

}
