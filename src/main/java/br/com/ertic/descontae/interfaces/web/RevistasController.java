package br.com.ertic.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Revista;
import br.com.ertic.descontae.domain.service.RevistaService;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/revistas")
public class RevistasController extends RestFullEndpoint<Revista, Long> {

    @Autowired
    public RevistasController(RevistaService service) {
        super(service);
    }

}