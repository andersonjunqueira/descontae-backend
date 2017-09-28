package br.com.ertic.descontae.interfaces.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Pessoa;
import br.com.ertic.descontae.domain.service.ConsumoService;
import br.com.ertic.descontae.domain.service.PessoaService;
import br.com.ertic.descontae.interfaces.web.dto.ConsumoUsuarioDTO;
import br.com.ertic.util.infraestructure.dto.Token;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.log.Log;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pessoas")
public class PessoasController extends RestFullEndpoint<Pessoa, Long> {

    @Autowired
    private Token token;

    @Autowired
    private ConsumoService consumoService;

    @Autowired
    public PessoasController(PessoaService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path="/login")
    public ResponseEntity<?> getByEmail(
        @RequestParam(name="email", required=true) String email) {
        try {
            Pessoa p = ((PessoaService)service).findByEmail(email);

            if(p != null) {
                return new ResponseEntity<>(p, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            Log.error(this.getClass(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path="/verificar")
    public ResponseEntity<Pessoa> verificar(
        @RequestParam(name="email", required=false) String email,
        @RequestParam(name="cpf", required=false) String cpf) {

        if(cpf == null && email == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean encontrado = ((PessoaService)service).verificar(cpf, email);

        if(!encontrado) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path="/consumos")
    public ResponseEntity<?> findConsumosUsuario(@PathVariable Long id) {
        try {

            List<ConsumoUsuarioDTO> saida = consumoService.findConsumosUsuario(token.getUsername());
            if(saida == null || saida.size() == 0) {
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
