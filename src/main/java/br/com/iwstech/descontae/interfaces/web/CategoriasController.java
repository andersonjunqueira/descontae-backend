package br.com.iwstech.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.domain.model.Categoria;
import br.com.iwstech.descontae.domain.service.CategoriaService;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categorias")
public class CategoriasController extends RestFullEndpoint<Categoria, Long> {

    @Autowired
    public CategoriasController(CategoriaService service) {
        super(service);
    }

}

