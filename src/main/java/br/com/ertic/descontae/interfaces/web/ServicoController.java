package br.com.ertic.descontae.interfaces.web;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/servico")
public class ServicoController {

    @RequestMapping(value="/ativo", method = RequestMethod.GET)
    public ResponseEntity<?> ativo() {
        return ResponseEntity.status(HttpStatus.SC_UNPROCESSABLE_ENTITY).build();
    }

}
