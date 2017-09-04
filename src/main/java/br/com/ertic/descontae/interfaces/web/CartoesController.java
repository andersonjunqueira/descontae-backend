package br.com.ertic.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Cartao;
import br.com.ertic.descontae.domain.service.CartaoService;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.log.Log;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cartoes")
public class CartoesController extends RestFullEndpoint<Cartao, Long> {

    @Autowired
    public CartoesController(CartaoService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.POST, path="/usuario")
    public ResponseEntity<?> add(@RequestBody Cartao input) {
        try {

           Cartao entity = ((CartaoService)service).associarCartao(input);
           return new ResponseEntity<>(entity, HttpStatus.OK);

       } catch (NegocioException ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

       } catch (Exception ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
}

