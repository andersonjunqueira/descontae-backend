package br.com.iwstech.descontae.interfaces.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.domain.model.Cartao;
import br.com.iwstech.descontae.domain.service.CartaoService;
import br.com.iwstech.util.infraestructure.dto.Token;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.log.Log;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cartoes")
public class CartoesController extends RestFullEndpoint<Cartao, Long> {

    @Autowired
    private Token token;

    @Autowired
    public CartoesController(CartaoService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path="/simples")
    public ResponseEntity<?> getListaSimples(HttpServletRequest request) {
        try {

            Page<Object[]> saida = ((CartaoService)service).findListaSimples(request.getParameterMap());
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

    @RequestMapping(method = RequestMethod.POST, path="/usuario")
    public ResponseEntity<?> add(@RequestBody Cartao input) {
        try {

            if(input.getCodigo() == null) {
                return new ResponseEntity<>("codigo-invalido", HttpStatus.BAD_REQUEST);
            }

           ((CartaoService)service).associarCartao(input.getCodigo(), token.getUsername());
           return new ResponseEntity<>(HttpStatus.OK);

       } catch (NegocioException ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

       } catch (Exception ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
}

