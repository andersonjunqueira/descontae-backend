package br.com.ertic.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Consumo;
import br.com.ertic.descontae.domain.service.ConsumoService;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/consumo")
public class ConsumosController extends RestFullEndpoint<Consumo, Long> {

    @Autowired
    public ConsumosController(ConsumoService service) {
        super(service);
    }

}

