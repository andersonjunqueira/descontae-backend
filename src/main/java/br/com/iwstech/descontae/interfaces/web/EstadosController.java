package br.com.iwstech.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.domain.model.Estado;
import br.com.iwstech.descontae.domain.service.EstadoService;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/estados")
public class EstadosController extends RestFullEndpoint<Estado, Long> {

    @Autowired
    public EstadosController(EstadoService service) {
        super(service);
    }

}

