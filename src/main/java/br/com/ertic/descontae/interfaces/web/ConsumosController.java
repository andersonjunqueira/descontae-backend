package br.com.ertic.descontae.interfaces.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ertic.descontae.domain.model.Consumo;
import br.com.ertic.descontae.domain.service.ConsumoService;
import br.com.ertic.descontae.interfaces.web.dto.ConsumoDTO;
import br.com.ertic.descontae.interfaces.web.dto.ConsumoUsuarioDTO;
import br.com.ertic.util.infraestructure.dto.Token;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.log.Log;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/consumos")
public class ConsumosController extends RestFullEndpoint<Consumo, Long> {

    @Autowired
    private Token token;

    @Autowired
    public ConsumosController(ConsumoService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.PUT, path="/oferta")
    public ResponseEntity<?> addOrUpdateEntity(
        HttpServletRequest request,
        UriComponentsBuilder b,
        @RequestBody ConsumoDTO input) {
        try {

            if(input.getOferta() == null || input.getOferta().getId() == null ) {
                return new ResponseEntity<>("oferta-invalida", HttpStatus.BAD_REQUEST);
            }

            if(input.getUnidade() == null || input.getUnidade().getId() == null || input.getUnidade().getSenhaValidacao() == null) {
                return new ResponseEntity<>("unidade-senha-invalidas", HttpStatus.BAD_REQUEST);
            }

            input.setEmail(token.getUsername());
            Consumo entity = ((ConsumoService)service).add(input);

            String cp = request.getContextPath();
            String location = request.getRequestURI() + "/{id}";
            location = location.substring(location.indexOf(cp) + cp.length());

            UriComponents uric = b.path(location).buildAndExpand(entity.getId());
            return ResponseEntity.created(uric.toUri()).build();

       } catch (NegocioException ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

       } catch (Exception ex) {
           Log.error(this.getClass(), ex);
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }

    @RequestMapping(method = RequestMethod.GET, path="/pessoa/{id}")
    public ResponseEntity<?> findConsumosUsuario(@PathVariable Long id) {
        try {

            List<ConsumoUsuarioDTO> saida = ((ConsumoService)service).findConsumosUsuario(id);
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

