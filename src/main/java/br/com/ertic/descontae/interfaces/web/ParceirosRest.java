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

import br.com.ertic.descontae.domain.service.ParceiroService;
import br.com.ertic.descontae.interfaces.web.dto.HomeDetalheDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.util.geo.TimeCount;

@RestController
@RequestMapping("/parceiros")
public class ParceirosRest  {

    @Autowired
    private ParceiroService srv;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public HomeDetalheDTO getParceiro(@PathVariable("id") Long id,
        HttpServletResponse response) {
        TimeCount tc =  TimeCount.start(this.getClass(), "Inicio do processamento do método /api/parceiros/{id}");

        HomeDetalheDTO saida = srv.findOne(id);

        if(null == saida){
           response.setStatus(HttpStatus.SC_NOT_FOUND);
        }

        tc.end();
        return saida;
    }

    @RequestMapping(value="/cidade/{idCidade}", method = RequestMethod.GET)
    public List<HomeParceiroDTO> findByCidade(
        @PathVariable("idCidade") Long idCidade,
        @RequestParam(required=false) Double lat,
        @RequestParam(required=false) Double lon,
        HttpServletResponse response) {
        TimeCount tc =  TimeCount.start(this.getClass(), "Inicio do processamento do método /api/parceiros/cidade/{id}");

        List<HomeParceiroDTO> saida = srv.findByCidade(idCidade, lat, lon);

        if(null == saida || saida.isEmpty()){
           response.setStatus(HttpStatus.SC_NO_CONTENT);
        }

        tc.end();
        return saida;
    }

}

