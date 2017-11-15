package br.com.iwstech.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.descontae.domain.service.ClienteService;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clientes")
public class ClientesController extends RestFullEndpoint<Cliente, Long> {

    @Autowired
    public ClientesController(ClienteService service) {
        super(service);
    }

}

