package br.com.iwstech.descontae.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.iwstech.descontae.domain.model.Configuracao;
import br.com.iwstech.descontae.domain.service.ConfiguracaoService;
import br.com.iwstech.util.infraestructure.exception.NegocioException;
import br.com.iwstech.util.infraestructure.log.Log;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/servico")
public class ServicoController {

    @Autowired
    private ConfiguracaoService service;

    @RequestMapping(value="/ativo", method = RequestMethod.GET)
    public ResponseEntity<?> cadastroAtivo() {
        try {

            Configuracao c = service.findOne(Configuracao.CONF_EXIBE_CADASTRO_COMPLETO.getId());
            if(c == null || !Boolean.parseBoolean(c.getValor())) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
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
