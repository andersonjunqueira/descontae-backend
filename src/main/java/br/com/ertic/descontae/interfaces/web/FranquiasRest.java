package br.com.ertic.descontae.interfaces.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.service.MarcaFranquiaService;
import br.com.ertic.descontae.interfaces.web.dto.HomeDetalheDTO;
import br.com.ertic.descontae.interfaces.web.dto.HomeParceiroDTO;
import br.com.ertic.util.geo.TimeCount;

@RestController
@RequestMapping("/franquias")
public class FranquiasRest  {

    @Autowired
    private MarcaFranquiaService srv;

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/cidade/{idCidade}", method = RequestMethod.GET)
    public List<HomeParceiroDTO> findAllByCidade(
        @PathVariable("idCidade") Long idCidade,
        @RequestParam(required=false) Double lat,
        @RequestParam(required=false) Double lon,
        HttpServletResponse response) {
        TimeCount tc =  TimeCount.start(this.getClass(), "Processamento do método /api/franquias/cidade/{id}");

        List<HomeParceiroDTO> saida = srv.findFranquiasByCidade(idCidade, lat, lon);

        if(null == saida || saida.isEmpty()){
           response.setStatus(HttpStatus.SC_NO_CONTENT);
        }

        tc.end();
        return saida;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/{id}/cidade/{idCidade}", method = RequestMethod.GET)
    public HomeDetalheDTO getParceiro(
        @PathVariable("id") Long id,
        @PathVariable("idCidade") Long idCidade,
        @RequestParam(required=false) Double lat,
        @RequestParam(required=false) Double lon,
        HttpServletResponse response) {
        TimeCount tc =  TimeCount.start(this.getClass(), "Processamento do método /api/franquias/{id}/cidade/{idCidade}");

        HomeDetalheDTO saida = srv.detalharFranquia(id, idCidade, lat, lon);

        if(null == saida){
           response.setStatus(HttpStatus.SC_NOT_FOUND);
        }

        tc.end();
        return saida;
    }

}

