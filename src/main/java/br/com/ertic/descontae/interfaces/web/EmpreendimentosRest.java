package br.com.ertic.descontae.interfaces.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Empreendimento;
import br.com.ertic.descontae.domain.service.EmpreendimentoService;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@RequestMapping("/empreendimentos")
public class EmpreendimentosRest extends RestFullEndpoint<Empreendimento, Long> {

    private EmpreendimentoService srv = (EmpreendimentoService)service;

    @Autowired
    public EmpreendimentosRest(EmpreendimentoService service) {
        super(service);
    }

    @RequestMapping(value="/local/{idCidade}", method = RequestMethod.GET)
    public List<Empreendimento> getDocument(
        @PathVariable("idCidade") Long idCidade,
        @RequestParam("lat") Double lat,
        @RequestParam("lon") Double lon,
        HttpServletResponse response) {

        List<Empreendimento> saida = srv.findEmpreendimentosRedondezas(idCidade, lat, lon);

        if(null == saida || saida.isEmpty()){
           response.setStatus(HttpStatus.SC_NO_CONTENT);
        }

        return saida;
    }

}

