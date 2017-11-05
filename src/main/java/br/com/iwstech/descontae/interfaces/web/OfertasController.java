package br.com.iwstech.descontae.interfaces.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.com.iwstech.descontae.domain.model.Oferta;
import br.com.iwstech.descontae.domain.service.OfertaService;
import br.com.iwstech.descontae.interfaces.web.dto.OfertaDTO;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.log.Log;
import br.com.iwstech.util.infraestructure.web.RestFullEndpoint;

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

    @RequestMapping(method = RequestMethod.GET, path="/{id}/dto")
    public ResponseEntity<?> getComUnidades(@PathVariable Long id) {
        try {

            OfertaDTO dto = ((OfertaService)service).getForEdit(id);
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

    @RequestMapping(method = RequestMethod.PUT, path="/dto")
    public ResponseEntity<?> saveDTO(
        HttpServletRequest request,
        UriComponentsBuilder b,
        @RequestBody OfertaDTO input) {
        try {

            boolean creation = input.getId() == null;

            Oferta entity = ((OfertaService)service).saveDTO(input);
            if(creation) {

                String cp = request.getContextPath();
                String location = request.getContextPath() + "/api/ofertas/{id}";
                location = location.substring(location.indexOf(cp) + cp.length());

                UriComponents uric = b.path(location).buildAndExpand(entity.getId());
                return ResponseEntity.created(uric.toUri()).build();

            } else {
                return new ResponseEntity<>(entity, HttpStatus.OK);
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

