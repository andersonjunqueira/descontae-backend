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
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.util.geo.TimeCount;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@RequestMapping("/parceiros")
public class ParceirosRest extends RestFullEndpoint<Empreendimento, Long> {

    private EmpreendimentoService srv = (EmpreendimentoService)service;

    @Autowired
    public ParceirosRest(EmpreendimentoService service) {
        super(service);
    }

    @RequestMapping(value="/cidade/{idCidade}", method = RequestMethod.GET)
    public List<HomeParceiroDTO> getDocument(
        @PathVariable("idCidade") Long idCidade,
        @RequestParam(required=false) Double lat,
        @RequestParam(required=false) Double lon,
        HttpServletResponse response) {

        TimeCount tc =  TimeCount.start("Inicio do processamento do método /api/parceiros/cidade/{id}");

        List<HomeParceiroDTO> saida = srv.findParceirosRedondezas(idCidade, lat, lon);

        if(null == saida || saida.isEmpty()){
           response.setStatus(HttpStatus.SC_NO_CONTENT);
        }

        tc.end();

        return saida;
    }

}

