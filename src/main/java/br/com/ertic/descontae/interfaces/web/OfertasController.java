package br.com.ertic.descontae.interfaces.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Oferta;
import br.com.ertic.descontae.domain.service.OfertaService;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.log.Log;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ofertas")
public class OfertasController extends RestFullEndpoint<Oferta, Long> {

    @Autowired
    public OfertasController(OfertaService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path="/simples")
    public ResponseEntity<?> getListaSimples(HttpServletRequest request) {
        try {

            Page<Object[]> saida = ((OfertaService)service).findListaSimples(request.getParameterMap());
            if(saida == null || saida.getTotalElements() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(saida, HttpStatus.OK);
            }

        } catch (NegocioException ex) {
            Log.error(this.getClass(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        } catch (Exception ex) {
            Log.error(this.getClass(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

