package br.com.iwstech.descontae.interfaces.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.domain.model.Unidade;
import br.com.iwstech.descontae.domain.service.UnidadeService;
import br.com.iwstech.descontae.interfaces.web.dto.OfertaUnidadeDTO;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.log.Log;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/unidades")
public class UnidadesController extends RestFullEndpoint<Unidade, Long> {

    @Autowired
    public UnidadesController(UnidadeService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path="/marca/{marcaId}/dto")
    public ResponseEntity<?> getUnidadesByMarca(@PathVariable Long marcaId) {
        try {

            List<OfertaUnidadeDTO> dto = ((UnidadeService)service).findUnidadesDTOByMarca(marcaId);
            if(dto == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(dto, HttpStatus.OK);
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
