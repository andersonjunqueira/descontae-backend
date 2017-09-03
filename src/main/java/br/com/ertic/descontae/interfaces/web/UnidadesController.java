package br.com.ertic.descontae.interfaces.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ertic.descontae.domain.model.Unidade;
import br.com.ertic.descontae.domain.service.UnidadeService;
import br.com.ertic.descontae.interfaces.web.dto.OfertaUnidadeDTO;
import br.com.ertic.util.infraestructure.exception.NegocioException;
import br.com.ertic.util.infraestructure.log.Log;
import br.com.ertic.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/unidades")
public class UnidadesController extends RestFullEndpoint<Unidade, Long> {

    @Autowired
    public UnidadesController(UnidadeService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path="/franquia/{marcaId}/oferta/{idOferta}")
    public ResponseEntity<?> getAllByFranquia(@PathVariable Long marcaId,
        @PathVariable Long ofertaId) {
        try {

            List<OfertaUnidadeDTO> saida = ((UnidadeService)service).findAllByMarcaSelecionadoPorOferta(marcaId, ofertaId);
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
