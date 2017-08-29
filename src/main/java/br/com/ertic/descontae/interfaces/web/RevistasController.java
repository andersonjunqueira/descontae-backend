package br.com.ertic.descontae.interfaces.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Revista;
import br.com.ertic.descontae.domain.service.RevistaService;
import br.com.ertic.descontae.interfaces.web.dto.OfertaDTO;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/revistas")
public class RevistasController extends RestFullEndpoint<Revista, Long> {

    @Autowired
    public RevistasController(RevistaService service) {
        super(service);
    }

    @RequestMapping(value="/{idRevista}/ofertas", method = RequestMethod.GET)
    public List<OfertaDTO> findOfertasByRevista(
        @PathVariable("idRevista") Long idRevista,
        HttpServletResponse response) {

        List<OfertaDTO> saida = ((RevistaService)service).findOfertasByRevista(idRevista);

        if(null == saida || saida.isEmpty()){
           response.setStatus(HttpStatus.SC_NO_CONTENT);
        }

        return saida;
    }

}