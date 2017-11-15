package br.com.iwstech.descontae.interfaces.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.Application;
import br.com.iwstech.descontae.domain.model.Cliente;
import br.com.iwstech.descontae.domain.service.ClienteService;
import br.com.iwstech.util.infraestructure.dto.Token;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.log.Log;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clientes")
public class ClientesController extends RestFullEndpoint<Cliente, Long> {

    @Autowired
    public ClientesController(ClienteService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pessoa")
    public ResponseEntity<?> reenvioSenha(HttpServletRequest request) {
        try {
            String accessToken = request.getHeader("Authorization");
            Token token = Application.readToken(accessToken);

            if(token != null) {
                Cliente c = ((ClienteService)service).findOneByPessoa(token);
                if(c != null) {
                    return new ResponseEntity<>(c, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("token-invalido", HttpStatus.UNPROCESSABLE_ENTITY);
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

