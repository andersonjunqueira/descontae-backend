package br.com.ertic.descontae.interfaces.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Cidade;
import br.com.ertic.descontae.domain.service.CidadeService;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cidades")
public class CidadesController extends RestFullEndpoint<Cidade, Long> {

    @Autowired
    public CidadesController(CidadeService service) {
        super(service);
    }

    @RequestMapping(value="/parcerias", method = RequestMethod.GET)
    public List<Cidade> getDocument(HttpServletResponse response) {
        List<Cidade> saida = ((CidadeService)service).findAllComParcerias();
        if(null == saida || saida.isEmpty()){
           response.setStatus(HttpStatus.SC_NO_CONTENT);
        }
        return saida;
    }

}

